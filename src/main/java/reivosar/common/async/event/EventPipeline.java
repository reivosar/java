package reivosar.common.async.event;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EventPipeline<E extends Event> {
    
    private static final int MAX_THREAD_SIZE = 100;

    private final EventStore<E> eventStore;
    private final EventProcessor<E> eventProcessor;
    private final ExecutorService pool;
    private final Collection<EventRunnable<E>> eventRunnableCollection;

    EventPipeline(final EventStore<E> eventStore, final EventProcessor<E> eventProcessor) {
        this.eventStore = eventStore;
        this.eventProcessor = eventProcessor;
        this.pool = Executors.newVirtualThreadPerTaskExecutor();
        this.eventRunnableCollection = Collections.synchronizedCollection(new LinkedList<>());
    }

    void push(final Collection<EventDescriptor<E>> eventDescriptors) {
        eventDescriptors.stream()
                .filter(this::isProcessableEventDescriptor)
                .forEach(
                        eventDescriptor -> eventRunnableCollection.add(
                                new EventRunnable<>(eventStore, eventProcessor, eventDescriptor)
                        )
                );
    }

    private boolean isProcessableEventDescriptor(final EventDescriptor<E> eventDescriptor) {
        if (eventRunnableCollection.size() >= MAX_THREAD_SIZE) {
            return false;
        }
        return eventRunnableCollection.stream()
                .noneMatch(eventRunnable -> eventRunnable.isSameEvent(eventDescriptor));
    }
    
    boolean hasPipelinedData() {
        return !eventRunnableCollection.isEmpty();
    }
    
    void start() {
        try {
            // Process start
            eventRunnableCollection.stream().filter(EventRunnable::isPending).forEach(pool::execute);
            // Process end
            eventRunnableCollection.removeIf(EventRunnable::isCompleted);
        } catch (Exception e) {
            stop();
        }
    }
    
    void stop() {
        pool.shutdownNow();
    }
}
