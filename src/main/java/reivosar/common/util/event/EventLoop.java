package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;

class EventLoop {
    
    private final DaemonThread thread;
    private final EventStore eventStore;
    private final EventPipeline eventPipeline;
    private final LockableFunction lockableFunction = new LockableFunction();
    private volatile boolean isRunning = false;
    
    private static final long EVENT_WAIT_TIME = 300;
    
    EventLoop(final EventStore eventStore, final EventProcessor eventProcessor) {
        this.eventStore = eventStore;
        this.eventPipeline = new EventPipeline(eventStore, eventProcessor);
        this.thread = new DaemonThread(this::run);
    }
    
    private void run() {
        while (isRunning) {
            eventPipeline.push(eventStore.getUncompletedEvents());
            if (eventPipeline.hasPipelinedData()) {
                eventPipeline.start();
                sleep();
            } else {
                try {
                    synchronized (this) {
                        wait(EVENT_WAIT_TIME);
                    }
                } catch (InterruptedException e) {
                    if (eventPipeline.hasPipelinedData() || eventStore.hasUncompletedEvent()) {
                        continue;
                    }
                    stop();
                }
            }
        }
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
    
    private void sleep() {
        try {
            Thread.sleep(EVENT_WAIT_TIME);
        } catch (InterruptedException e) {
            // Do nothing
        }
    }
    
    void stop() {
        lockableFunction.withLock(() -> {
            isRunning = false;
            eventPipeline.stop();
        });
    }
}
