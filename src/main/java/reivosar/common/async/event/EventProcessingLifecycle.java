package reivosar.common.async.event;

/**
 * Manages the lifecycle of an event processing system.
 * <p>
 * Implementations of this interface are responsible for controlling
 * the start, stop, and execution status of an event processing mechanism,
 * such as an event dispatcher, worker, or execution loop.
 */
public interface EventProcessingLifecycle {

    /**
     * Starts the event processing.
     * <p>
     * This method initializes and begins the event processing system,
     * enabling it to process incoming events.
     * Implementations should ensure that necessary resources are allocated
     * and that event processing is properly initialized.
     */
    void start();

    /**
     * Stops the event processing.
     * <p>
     * This method terminates the event processing system, ensuring that
     * all ongoing tasks are completed or safely halted before shutting down.
     * Implementations should release any acquired resources and prevent
     * further event processing.
     */
    void stop();

    /**
     * Checks whether the event processing system is currently active.
     * <p>
     * This method returns {@code true} if the event processing system is actively processing events,
     * otherwise returns {@code false}.
     *
     * @return {@code true} if the event processing system is actively processing events, {@code false} otherwise
     */
    boolean isProcessing();
}
