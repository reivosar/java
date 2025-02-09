package reivosar.common.async.options;

import java.util.function.Function;

/**
 * Defines the configuration options for asynchronous processing.
 * <p>
 * This interface provides settings for controlling how asynchronous tasks, such as event publishing or promise resolution,
 * are handled. These options include:
 * <ul>
 *     <li>Whether to process tasks concurrently (multiple processing)</li>
 *     <li>The concurrency level (multiplicity)</li>
 *     <li>The execution timeout</li>
 *     <li>The maximum number of retry attempts</li>
 *     <li>The error handling strategy</li>
 * </ul>
 * Implementations of {@code AsyncOptions} allow fine-grained control over task execution behavior.
 * </p>
 */
public interface AsyncOptions {

    /**
     * Creates and returns a new {@link Builder} instance.
     *
     * @return a new {@link Builder} instance for configuring {@link AsyncOptions}
     */
    static Builder builder() {
        return new DefaultAsyncOptions.Builder();
    }

    /**
     * Determines whether multiple concurrent processing is enabled.
     * <p>
     * When enabled, multiple tasks can be executed concurrently instead of sequentially.
     * </p>
     *
     * @return {@code true} if multiple processing is enabled; {@code false} otherwise
     */
    boolean isMultiple();

    /**
     * Returns the concurrency level for multiple processing.
     * <p>
     * If multiple processing is enabled, this value should be greater than 1.
     * Otherwise, it defaults to sequential execution.
     * </p>
     *
     * @return the concurrency level; {@code null} if not set
     */
    Integer getMultiplicity();

    /**
     * Returns the maximum number of retry attempts in case of failure.
     * <p>
     * If a task fails, it may be retried up to this number of times before being considered failed.
     * </p>
     *
     * @return the retry limit; {@code null} if no retry limit is set
     */
    Integer getRetryLimit();

    /**
     * Returns the maximum execution timeout in milliseconds.
     * <p>
     * If a task exceeds this duration, it may be terminated depending on the implementation.
     * </p>
     *
     * @return the execution timeout in milliseconds; {@code null} if no timeout is set
     */
    Long getTimeout();

    /**
     * Returns the error handling strategy for failed tasks.
     * <p>
     * This strategy determines how errors are managed, including whether failures should be ignored,
     * logged, or cause task termination.
     * </p>
     *
     * @return the error handling strategy; {@code null} if not specified
     */
    ErrorHandlingStrategy getErrorHandlingStrategy();

    /**
     * Copies the settings from another {@link AsyncOptions} instance.
     * <p>
     * This method updates the current instance with values from the given options.
     * If {@code options} is {@code null}, no changes are made.
     * </p>
     *
     * @param options the {@link AsyncOptions} instance to copy settings from
     */
    void copyFrom(AsyncOptions options);

    /**
     * Builder interface for constructing {@link AsyncOptions} instances.
     */
    interface Builder {

        /**
         * Configures options for single-threaded (sequential) processing.
         * <p>
         * The provided configurator function receives a {@link SingleAsyncOptions} instance,
         * allowing customization of properties such as retry count.
         * </p>
         *
         * @param configurator a function to configure sequential processing options
         * @return the current {@link Builder} instance for method chaining
         */
        Builder single(Function<SingleAsyncOptions, SingleAsyncOptions> configurator);

        /**
         * Configures options for multi-thread (parallel) processing.
         * <p>
         * The provided configurator function receives a {@link MultiAsyncOptions} instance,
         * allowing customization of concurrency level and error handling strategy.
         * </p>
         *
         * @param configurator a function to configure parallel processing options
         * @return the current {@link Builder} instance for method chaining
         */
        Builder multi(Function<MultiAsyncOptions, MultiAsyncOptions> configurator);

        /**
         * Builds and returns a new {@link AsyncOptions} instance with the configured settings.
         *
         * @return a new {@link AsyncOptions} instance
         */
        AsyncOptions build();
    }
}
