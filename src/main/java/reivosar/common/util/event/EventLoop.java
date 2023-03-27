package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;

import java.util.Collection;

class EventLoop {
    
    private final Thread thread;
    private final EventStore eventStore;
    private final EventPipeline eventPipeline;
    private final LockableFunction lockableFunction = new LockableFunction();
    private volatile boolean isRunning = false;
    
    private static final long SLEEP_TIME = 2000;
    
    EventLoop(final EventStore eventStore, final EventProcessor eventProcessor) {
        this.eventStore = eventStore;
        this.eventPipeline = new EventPipeline(eventStore, eventProcessor);
        this.thread = new Thread(this::run);
    }
    
    private void run() {
        while (isRunning) {
            final Collection<EventDescriptor> events = eventStore.getUncompletedEvents();
            if (events.isEmpty()) {
                try {
                    synchronized (this) {
                        wait(SLEEP_TIME);
                    }
                } catch (InterruptedException e) {
                    if (eventStore.hasUncompletedEvent()) {
                        continue;
                    }
                    stop();
                }
            } else {
                processEvents(events);
            }
        }
    }
    
    private void processEvents(final Collection<EventDescriptor> eventDescriptors) {
        eventDescriptors.forEach(eventDescriptor -> {
            final EventDescriptor processEventDescriptor = eventDescriptor.isPublished() ?
                    eventDescriptor : DefaultEventDescriptor.publishedBy(eventDescriptor);
            if (eventPipeline.beforeProcess(processEventDescriptor)) {
                try {
                    eventPipeline.process(processEventDescriptor);
                } finally {
                    eventPipeline.afterProcess(processEventDescriptor);
                }
            }
        });
    }
    
    void start() {
        lockableFunction.withLock(() -> {
            if (isRunning) {
                return;
            }
            isRunning = true;
            thread.start();
        });
    }
    
    void stop() {
        lockableFunction.withLock(() -> {
            isRunning = false;
        });
    }
}
