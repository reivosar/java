package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;
import reivosar.common.util.log.Loggers;

class EventLoop {
    
    private final Loggers LOGGER = Loggers.getLoggers(EventRunnable.class);
    
    private final DaemonThread thread;
    private final EventStore eventStore;
    private final EventPipeline eventPipeline;
    private final LockableFunction lockableFunction = new LockableFunction();
    private volatile boolean isRunning = false;
    
    private static final long EVENT_WAIT_TIME = 1000;
    
    EventLoop(final EventStore eventStore, final EventProcessor eventProcessor) {
        this.eventStore = eventStore;
        this.eventPipeline = new EventPipeline(eventStore, eventProcessor);
        this.thread = new DaemonThread(this::run);
    }
    
    private void run() {
        while (isRunning) {
            eventPipeline.push(eventStore.getUncompletedEvents());
            if (eventPipeline.hasPipelinedData()) {
                eventPipeline.process();
                sleep();
            } else {
                try {
                    synchronized (this) {
                        wait(EVENT_WAIT_TIME);
                    }
                } catch (InterruptedException e) {
                    if (eventStore.hasUncompletedEvent()) {
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
        });
    }
}
