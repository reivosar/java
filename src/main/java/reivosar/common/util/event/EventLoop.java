package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;

import java.util.Collection;

class EventLoop {
    
    private final Thread thread;
    private final LockableFunction lockableFunction = new LockableFunction();
    private volatile boolean isRunning = false;
    
    private static final long SLEEP_TIME = 2000;
    
    EventLoop(final EventStore eventStore, final EventProcessor eventProcessor) {
        this.thread = new Thread(()-> {
            while (isRunning) {
                final Collection<StoredEvent> events = eventStore.getAll();
                if (events.isEmpty()) {
                    try {
                        Thread.sleep(SLEEP_TIME);
                    } catch (InterruptedException e) {
                        stop();
                        if (eventStore.hasEvent()) {
                            start();
                        }
                    }
                } else {
                    for (final StoredEvent storedEvent : events) {
                        eventProcessor.process(storedEvent.getEvent());
                        eventStore.remove(storedEvent);
                    }
                }
            }
        });
    }
    
    void start() {
        lockableFunction.with(() -> {
            if (isRunning) {
                return;
            }
            isRunning = true;
            thread.start();
        });
    }
    
    void stop() {
        lockableFunction.with(() -> {
            if (!isRunning) {
                return;
            }
            isRunning = false;
            thread.interrupt();
        });
    }
}
