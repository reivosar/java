package reivosar.common.async.event;

import reivosar.common.lang.function.LockableFunction;

class EventLoop {
    
    private final DaemonThread thread;
    private final EventStore eventStore;
    private final EventPipeline eventPipeline;
    private final LockableFunction lockableFunction;
    private volatile boolean isRunning = false;
    
    private static final long EVENT_WAIT_TIME = 300;
    
    EventLoop(final EventStore eventStore, final EventProcessor eventProcessor) {
        this.eventStore = eventStore;
        this.eventPipeline = new EventPipeline(eventStore, eventProcessor);
        this.lockableFunction = new LockableFunction();
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
        lockableFunction.lockOn(() -> {
            if (isRunning || thread.isAlive()) {
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
        lockableFunction.lockOn(() -> {
            isRunning = false;
            eventPipeline.stop();
        });
    }
}
