package reivosar.common.util.event;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class EventPipeline {
    
    private static final int MAX_THREAD_SIZE = 100;
    
    private final EventStore eventStore;
    private final EventProcessor eventProcessor;
    private final ExecutorService pool;
    private final Collection<EventRunnable> eventRunnableCollection;
    
    EventPipeline(final EventStore eventStore, final EventProcessor eventProcessor) {
        this.eventStore = eventStore;
        this.eventProcessor = eventProcessor;
        this.pool = Executors.newVirtualThreadPerTaskExecutor();
        this.eventRunnableCollection = Collections.synchronizedCollection(new LinkedList<>());
    }
    
    void push(final Collection<EventDescriptor> eventDescriptors) {
        eventDescriptors.stream()
                .filter(this::isProcessableEventDescriptor)
                .forEach(
                        eventDescriptor -> eventRunnableCollection.add(
                                new EventRunnable(eventStore, eventProcessor, eventDescriptor)
                        )
                );
    }
    
    private boolean isProcessableEventDescriptor(final EventDescriptor eventDescriptor) {
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
