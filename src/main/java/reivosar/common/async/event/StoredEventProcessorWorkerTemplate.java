package reivosar.common.async.event;

import reivosar.common.lang.function.LockableFunction;

/**
 * An abstract template class for an event processor worker that continuously processes stored events.
 * <p>
 * This class manages the lifecycle of an event processing worker, handling event retrieval
 * from an {@link EventStore} and processing them using an {@link EventPipeline}.
 * It runs in a background daemon thread and ensures that events are processed asynchronously.
 *
 * @param <E> the type of event to be processed
 */
public abstract class StoredEventProcessorWorkerTemplate<E extends Event> implements EventProcessingLifecycle {

    private static final long EVENT_WAIT_TIME = 300;
    private final DaemonThread thread;
    private final EventPipeline<E> eventPipeline;
    private final LockableFunction lockableFunction;
    private volatile boolean isProcessing = false;

    /**
     * Constructs an {@code EventStoredProcessorWorkerTemplate} with the specified {@link EventStore}
     *
     * @param eventStore the event store that holds unprocessed events
     */
    protected StoredEventProcessorWorkerTemplate(final EventStore<E> eventStore) {
        this.eventPipeline = new EventPipeline<>(eventStore);
        this.lockableFunction = new LockableFunction();
        this.thread = new DaemonThread(this::run);
    }

    private void run() {
        while (isProcessing) {
            eventPipeline.fetchEvents();
            if (eventPipeline.hasUncompletedEvent()) {
                eventPipeline.processAndCleanupEvents();
                sleep();
            } else {
                try {
                    synchronized (this) {
                        wait(EVENT_WAIT_TIME);
                    }
                } catch (InterruptedException e) {
                    if (eventPipeline.hasUncompletedEvent()) {
                        continue;
                    }
                    stop();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public final void start() {
        lockableFunction.lockOn(() -> {
            if (isProcessing || thread.isAlive()) {
                return;
            }
            isProcessing = true;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isProcessing() {
        return isProcessing;
    }

    /**
     * {@inheritDoc}
     */
    public final void stop() {
        lockableFunction.lockOn(() -> {
            isProcessing = false;
            eventPipeline.shutdownProcessor();
        });
    }
}
