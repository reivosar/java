package reivosar.common.async.options;

/**
 * Defines configuration options for single-threaded (sequential) asynchronous processing.
 * <p>
 * This interface provides options for managing execution behavior when tasks are processed sequentially.
 * Settings include:
 * <ul>
 *     <li>The maximum number of retry attempts if a task fails</li>
 *     <li>An optional timeout for task execution</li>
 *     <li>An optional error handling strategy</li>
 * </ul>
 * These options allow fine-grained control over execution behavior for single-processing tasks.
 * </p>
 */
public interface SingleAsyncOptions {

    /**
     * Returns the maximum number of retry attempts.
     * <p>
     * If no retry limit is set, the default value may be used by the implementation.
     * </p>
     *
     * @return the retry limit, or {@code null} if not set
     */
    Integer getRetryLimit();

    /**
     * Returns the execution timeout in milliseconds.
     * <p>
     * If no timeout is set, the default value may be used by the implementation.
     * </p>
     *
     * @return the execution timeout, or {@code null} if not set
     */
    Long getTimeout();

    /**
     * Returns the error handling strategy for single-processing tasks.
     * <p>
     * Implementations may use this strategy to define how errors are handled.
     * If no strategy is set, a default strategy may be applied.
     * </p>
     *
     * @return the error handling strategy, or {@code null} if not set
     */
    ErrorHandlingStrategy getErrorHandlingStrategy();

    /**
     * Builder interface for constructing {@link SingleAsyncOptions} instances.
     */
    interface Builder {

        /**
         * Sets the maximum number of retry attempts if a task fails.
         * <p>
         * If set, the task will be retried up to the specified number of times before being considered failed.
         * </p>
         *
         * @param retryLimit the maximum number of retry attempts; must be non-negative
         * @return this instance for method chaining
         * @throws IllegalArgumentException if {@code retryLimit} is negative
         */
        Builder retryLimit(int retryLimit);

        /**
         * Sets the execution timeout in milliseconds.
         * <p>
         * If set, the task execution will be limited to the specified duration.
         * If the task exceeds this time, it may be terminated depending on the implementation.
         * </p>
         *
         * @param timeout the maximum execution time in milliseconds; must be positive
         * @return this instance for method chaining
         * @throws IllegalArgumentException if {@code timeout} is negative
         */
        Builder timeout(Long timeout);

        /**
         * Sets the error handling strategy for single-processing tasks.
         * <p>
         * This allows defining how failures should be handled, such as ignoring, logging,
         * or retrying under specific conditions.
         * </p>
         *
         * @param strategy the error handling strategy
         * @return this instance for method chaining
         */
        Builder errorHandlingStrategy(ErrorHandlingStrategy strategy);

        /**
         * Builds and returns a new {@link SingleAsyncOptions} instance with the configured settings.
         *
         * @return a new {@link SingleAsyncOptions} instance
         */
        SingleAsyncOptions build();
    }
}
