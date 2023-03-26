package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;

import java.util.Collection;

class EventLoop {
    
    private final EventStore eventStore;
    private final EventProcessor eventProcessor;
    private final DaemonThread loopThread;
    private final LockableFunction lockableFunction = new LockableFunction();
    private volatile boolean isRunning = false;
    
    private static final long SLEEP_TIME = 1000;
    
    public EventLoop(final EventStore eventStore, final EventProcessor eventProcessor) {
        this.eventStore = eventStore;
        this.eventProcessor = eventProcessor;
        this.loopThread = new DaemonThread(EventPriority.MAX, ()-> {
            while (isRunning) {
                Collection<Event> events = eventStore.getAll();
                if (events.isEmpty()) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    for (Event event : events) {
                        //TODO
                    }
                }
            }
        });
    }
    
    public void start() {
        lockableFunction.with(() -> {
            if (isRunning) {
                return;
            }
            isRunning = true;
            loopThread.start();
        });
    }
    
    public void stop() {
        lockableFunction.with(() -> {
            if (!isRunning) {
                return;
            }
            isRunning = false;
            loopThread.interrupt();
        });
    }
}
