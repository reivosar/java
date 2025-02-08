package reivosar.common.async.event;

/**
 * Defines options for multiple (parallel) event processing.
 */
public interface MultiPublishOptions {

    /**
     * Sets the concurrency level (multiplicity) for parallel processing.
     *
     * @param multiplicity the number of concurrent threads or tasks.
     * @return this instance for chaining.
     */
    MultiPublishOptions multiplicity(Integer multiplicity);

    /**
     * Sets the error handling strategy for parallel processing.
     *
     * @param strategy the error handling strategy.
     * @return this instance for chaining.
     */
    MultiPublishOptions errorHandlingStrategy(ErrorHandlingStrategy strategy);

    /**
     * Returns the configured multiplicity.
     *
     * @return the concurrency level.
     */
    Integer getMultiplicity();

    /**
     * Returns the configured error handling strategy.
     *
     * @return the error handling strategy.
     */
    ErrorHandlingStrategy getErrorHandlingStrategy();
}
