package reivosar.common.async.event;

import java.util.function.Function;

/**
 * Defines the options used for publishing events.
 * <p>
 * Implementations of an {@code EventPublisher} may use these options to determine how events are processed.
 * The options include whether to process events concurrently (multiple processing),
 * the concurrency level (multiplicity), the number of retry attempts, and the error handling strategy.
 * </p>
 */
public interface PublishOptions {

    /**
     * Convenience method to obtain a new Builder.
     *
     * @return a new Builder instance.
     */
    static Builder builder() {
        return new DefaultPublishOptions.Builder();
    }

    /**
     * Returns {@code true} if multiple processing is enabled.
     *
     * @return {@code true} for multiple processing; {@code false} otherwise.
     */
    boolean isMultiple();

    /**
     * Returns the concurrency level (multiplicity). If multiple processing is enabled,
     * this value should be greater than 1.
     *
     * @return the concurrency level.
     */
    int getMultiplicity();

    /**
     * Returns the number of retry attempts.
     *
     * @return the retry count.
     */
    int getRetry();

    /**
     * Returns the error handling strategy.
     *
     * @return the error handling strategy.
     */
    ErrorHandlingStrategy getErrorHandlingStrategy();

    /**
     * Copies the settings from the given {@link PublishOptions} instance.
     * <p>
     * This method updates the current instance with the values from the provided options.
     * If {@code options} is {@code null}, no changes are made.
     * </p>
     *
     * @param options the {@link PublishOptions} to copy from.
     */
    void copyFrom(PublishOptions options);

    /**
     * Builder interface for constructing {@link PublishOptions} instances.
     */
    interface Builder {
        /**
         * Configures options for single (sequential) processing.
         * <p>
         * The provided configurator function receives a {@link SinglePublishOptions} instance,
         * which can be used to set options such as the retry count.
         * </p>
         *
         * @param configurator a function to configure single processing options.
         * @return the current Builder instance for chaining.
         */
        Builder single(Function<SinglePublishOptions, SinglePublishOptions> configurator);

        /**
         * Configures options for multiple (parallel) processing.
         * <p>
         * The provided configurator function receives a {@link MultiPublishOptions} instance,
         * which can be used to set the concurrency (multiplicity) and error handling strategy.
         * </p>
         *
         * @param configurator a function to configure multiple processing options.
         * @return the current Builder instance for chaining.
         */
        Builder multi(Function<MultiPublishOptions, MultiPublishOptions> configurator);

        /**
         * Builds and returns a new {@link PublishOptions} instance with the configured settings.
         *
         * @return a new PublishOptions instance.
         */
        PublishOptions build();
    }
}
