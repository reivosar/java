package reivosar.common.async.options;

/**
 * Defines configuration options for multi-threaded (parallel) asynchronous processing.
 * <p>
 * This interface provides options for managing execution behavior when tasks are processed in parallel.
 * Settings include:
 * <ul>
 *     <li>The maximum number of retry attempts if a task fails</li>
 *     <li>The execution timeout for task completion</li>
 *     <li>The concurrency level (multiplicity)</li>
 *     <li>An optional error handling strategy</li>
 * </ul>
 * These options allow fine-grained control over execution behavior for multi-processing tasks.
 * </p>
 */
public interface MultiAsyncOptions {

    /**
     * Sets the maximum number of retry attempts if a task fails.
     * <p>
     * If set, a task will be retried up to the specified number of times before being considered failed.
     * </p>
     *
     * @param retryLimit the maximum number of retry attempts; must be non-negative
     * @return this instance for method chaining
     * @throws IllegalArgumentException if {@code retryLimit} is negative
     */
    MultiAsyncOptions retryLimit(Integer retryLimit);

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
    MultiAsyncOptions timeout(Long timeout);

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
     * Sets the concurrency level (multiplicity) for parallel processing.
     * <p>
     * This value determines how many tasks can be executed concurrently.
     * If parallel processing is enabled, this value should be greater than 1.
     * </p>
     *
     * @param multiplicity the number of concurrent threads or tasks; must be greater than 0
     * @return this instance for method chaining
     * @throws IllegalArgumentException if {@code multiplicity} is less than 1
     */
    MultiAsyncOptions multiplicity(Integer multiplicity);

    /**
     * Returns the configured concurrency level for parallel processing.
     * <p>
     * If no multiplicity is set, the default value may be used by the implementation.
     * </p>
     *
     * @return the concurrency level, or {@code null} if not set
     */
    Integer getMultiplicity();

    /**
     * Sets the error handling strategy for parallel processing.
     * <p>
     * This allows defining how failures should be handled, such as ignoring, logging,
     * or retrying under specific conditions.
     * </p>
     *
     * @param strategy the error handling strategy
     * @return this instance for method chaining
     */
    MultiAsyncOptions errorHandlingStrategy(ErrorHandlingStrategy strategy);

    /**
     * Returns the error handling strategy for multi-processing tasks.
     * <p>
     * Implementations may use this strategy to define how errors are handled.
     * If no strategy is set, a default strategy may be applied.
     * </p>
     *
     * @return the error handling strategy, or {@code null} if not set
     */
    ErrorHandlingStrategy getErrorHandlingStrategy();
}
