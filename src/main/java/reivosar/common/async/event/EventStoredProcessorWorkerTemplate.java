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
public abstract class EventStoredProcessorWorkerTemplate<E extends Event> implements EventProcessingLifecycle {

    private static final long EVENT_WAIT_TIME = 300;
    private final DaemonThread thread;
    private final EventStore<E> eventStore;
    private final EventPipeline<E> eventPipeline;
    private final LockableFunction lockableFunction;
    private volatile boolean isProcessing = false;

    /**
     * Constructs an {@code EventStoredProcessorWorkerTemplate} with the specified {@link EventStore}
     * and {@link EventProcessor}.
     *
     * @param eventStore     the event store that holds unprocessed events
     * @param eventProcessor the processor responsible for handling events
     */
    protected EventStoredProcessorWorkerTemplate(final EventStore<E> eventStore,
                                                 final EventProcessor<E> eventProcessor) {
        this.eventStore = eventStore;
        this.eventPipeline = new EventPipeline<>(eventStore, eventProcessor);
        this.lockableFunction = new LockableFunction();
        this.thread = new DaemonThread(this::run);
    }

    private void run() {
        while (isProcessing) {
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

    public final void start() {
        lockableFunction.lockOn(() -> {
            if (isProcessing || thread.isAlive()) {
                return;
            }
            isProcessing = true;
            thread.start();
        });
    }

    /**
     * {@inheritDoc}
     */
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
            eventPipeline.stop();
        });
    }
}
