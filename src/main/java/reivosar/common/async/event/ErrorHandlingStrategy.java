package reivosar.common.async.event;

/**
 * Defines the error handling strategy for event processing.
 * <p>
 * This enum provides different strategies to determine how errors are handled during event publishing.
 * It includes options to stop processing, continue despite errors, or retry the operation.
 * </p>
 */
public enum ErrorHandlingStrategy {

    /**
     * Stops processing immediately when an error occurs.
     * <p>
     * This strategy ensures that no further events are processed once an error is encountered.
     * </p>
     */
    STOP_ON_ERROR,

    /**
     * Skips the failed event and continues processing the remaining events.
     * <p>
     * This strategy allows processing to continue even if some events fail.
     * </p>
     */
    SKIP_AND_CONTINUE,

    /**
     * Retries the failed event before continuing.
     * <p>
     * If an error occurs, the event will be retried based on the configured retry attempts.
     * If the retries are exhausted, the processing continues to the next event.
     * </p>
     */
    RETRY_AND_CONTINUE;

    /**
     * Checks if the strategy stops processing upon encountering an error.
     *
     * @return {@code true} if the strategy is {@link #STOP_ON_ERROR}, otherwise {@code false}.
     */
    public boolean isStopOnError() {
        return this == STOP_ON_ERROR;
    }

    /**
     * Checks if the strategy allows processing to continue despite errors.
     * <p>
     * Both {@link #SKIP_AND_CONTINUE} and {@link #RETRY_AND_CONTINUE} allow the process to continue.
     * </p>
     *
     * @return {@code true} if processing should continue despite errors, otherwise {@code false}.
     */
    public boolean isContinueOnError() {
        return this == SKIP_AND_CONTINUE || this == RETRY_AND_CONTINUE;
    }

    /**
     * Checks if the strategy includes retrying failed events before continuing.
     *
     * @return {@code true} if the strategy is {@link #RETRY_AND_CONTINUE}, otherwise {@code false}.
     */
    public boolean isRetryOnError() {
        return this == RETRY_AND_CONTINUE;
    }
}
