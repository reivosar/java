package reivosar.common.async.event;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EventPipeline<E extends Event> {

    private static final int DEFAULT_MAX_THREAD_SIZE = 100;

    private final int maxThreadSize;
    private final EventStore<E> eventStore;
    private final EventProcessor<E> eventProcessor;
    private final ExecutorService pool;
    private final Collection<EventRunnable<E>> eventRunnableCollection;

    EventPipeline(final EventStore<E> eventStore, final EventProcessor<E> eventProcessor) {
        this(eventStore, eventProcessor, DEFAULT_MAX_THREAD_SIZE);
    }

    EventPipeline(final EventStore<E> eventStore, final EventProcessor<E> eventProcessor, final int maxThreadSize) {
        this.eventStore = eventStore;
        this.eventProcessor = eventProcessor;
        this.pool = Executors.newVirtualThreadPerTaskExecutor();
        this.eventRunnableCollection = Collections.synchronizedCollection(new LinkedList<>());
        this.maxThreadSize = maxThreadSize;
    }

    void enqueueEvents(final Collection<EventDescriptor<E>> eventDescriptors) {
        eventDescriptors.stream()
                .filter(this::isProcessableEventDescriptor)
                .forEach(eventDescriptor -> eventRunnableCollection.add(
                        new EventRunnable<>(eventStore, eventProcessor, eventDescriptor)
                ));
    }

    private boolean isProcessableEventDescriptor(final EventDescriptor<E> eventDescriptor) {
        if (eventRunnableCollection.size() >= maxThreadSize) {
            return false;
        }
        return eventRunnableCollection.stream()
                .noneMatch(eventRunnable -> eventRunnable.isSameEvent(eventDescriptor));
    }

    boolean hasPendingEvents() {
        return !eventRunnableCollection.isEmpty();
    }

    void processAndCleanupEvents() {
        try {
            // Start processing events
            eventRunnableCollection.stream()
                    .filter(EventRunnable::isPending)
                    .forEach(pool::execute);

            // Remove completed events from the queue
            eventRunnableCollection.removeIf(EventRunnable::isCompleted);
        } catch (Exception e) {
            shutdownProcessor();
        }
    }

    void shutdownProcessor() {
        pool.shutdownNow();
    }
}

