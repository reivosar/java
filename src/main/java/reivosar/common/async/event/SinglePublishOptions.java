package reivosar.common.async.event;

/**
 * Defines options for single (sequential) event processing.
 */
public interface SinglePublishOptions {

    /**
     * Sets the number of retry attempts for single processing.
     *
     * @param retry the retry count.
     * @return this instance for chaining.
     */
    SinglePublishOptions retry(Integer retry);

    /**
     * Returns the configured retry count.
     *
     * @return the retry count.
     */
    Integer getRetry();

    /**
     * (Optional) Returns the error handling strategy for single processing.
     * For this DSL, single processing may not require a separate error strategy.
     *
     * @return the error handling strategy.
     */
    ErrorHandlingStrategy getErrorHandlingStrategy();

    /**
     * (Optional) Allows setting an error handling strategy for single processing.
     *
     * @param strategy the error handling strategy.
     * @return this instance for chaining.
     */
    SinglePublishOptions errorHandlingStrategy(ErrorHandlingStrategy strategy);
}
