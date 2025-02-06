package reivosar.common.async.event;

import reivosar.common.async.promise.Promise;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;

/**
 * Represents a publisher responsible for publishing events.
 * <p>
 * This functional interface provides methods to publish single or multiple events asynchronously,
 * returning a {@link Promise} to handle the completion of the publishing process.
 * Implementations of this interface define how events are dispatched, processed, or stored.
 * </p>
 *
 * @param <E> the type of event to be published, which extends the {@link Event} interface
 */
public interface EventPublisher<E extends Event, This extends EventPublisher<E, This>> {

    /**
     * Publishes a single event.
     * <p>
     * This method is a convenience wrapper for the {@link #publish(Collection)} method,
     * allowing a single event to be published by internally wrapping it in a {@link Collections#singletonList(Object)}.
     * </p>
     *
     * @param event the event to publish; must not be {@code null}
     * @return a {@link Promise} that resolves when the event has been successfully published
     * @throws NullPointerException if {@code event} is {@code null}
     */
    default Promise<Void> publish(E event) {
        return publish(Collections.singletonList(event));
    }

    /**
     * Publishes a collection of events.
     * <p>
     * This method asynchronously publishes multiple events. Implementations should define
     * how the events are dispatched and handled.
     * </p>
     *
     * @param events the collection of events to publish; must not be {@code null} or contain {@code null} elements
     * @return a {@link Promise} that resolves when all events have been successfully published
     * @throws NullPointerException if {@code events} is {@code null} or contains {@code null} elements
     */
    Promise<Void> publish(Collection<E> events);

    /**
     * Configures the publisher for single (sequential) event processing with default options.
     * <p>
     * This method returns an instance of {@code EventPublisher} configured for sequential processing
     * using default {@link SinglePublishOptions}.
     * </p>
     *
     * @return an instance of {@code EventPublisher} configured for single event processing.
     */
    default This single() {
        return single(opts -> opts);
    }

    /**
     * Configures the publisher for single (sequential) event processing with custom options.
     * <p>
     * The provided configurator function receives a {@link SinglePublishOptions} instance,
     * which can be used to configure retry attempts and other options specific to sequential event processing.
     * </p>
     *
     * @param configurator a function to configure single processing options.
     * @return an instance of {@code EventPublisher} configured with the given options.
     */
    default This single(Function<SinglePublishOptions, SinglePublishOptions> configurator) {
        PublishOptions options = PublishOptions.builder()
                .single(configurator)
                .build();
        return single(options);
    }

    /**
     * Configures the publisher for single (sequential) event processing with predefined {@link PublishOptions}.
     * <p>
     * This method allows setting explicit options instead of using a configurator function.
     * </p>
     *
     * @param options the {@link PublishOptions} for single event processing.
     * @return an instance of {@code EventPublisher} configured with the given options.
     */
    This single(PublishOptions options);

    /**
     * Configures the publisher for multiple (parallel) event processing with default options.
     * <p>
     * This method returns an instance of {@code EventPublisher} configured for parallel processing
     * using default {@link MultiPublishOptions}.
     * </p>
     *
     * @return an instance of {@code EventPublisher} configured for multiple event processing.
     */
    default This multi() {
        return multi(opts -> opts);
    }

    /**
     * Configures the publisher for multiple (parallel) event processing with custom options.
     * <p>
     * The provided configurator function receives a {@link MultiPublishOptions} instance,
     * which can be used to configure concurrency levels and error handling strategies for parallel event processing.
     * </p>
     *
     * @param configurator a function to configure multiple processing options.
     * @return an instance of {@code EventPublisher} configured with the given options.
     */
    default This multi(Function<MultiPublishOptions, MultiPublishOptions> configurator) {
        PublishOptions options = PublishOptions.builder()
                .multi(configurator)
                .build();
        return multi(options);
    }

    /**
     * Configures the publisher for multiple (parallel) event processing with predefined {@link PublishOptions}.
     * <p>
     * This method allows setting explicit options instead of using a configurator function.
     * </p>
     *
     * @param options the {@link PublishOptions} for multiple event processing.
     * @return an instance of {@code EventPublisher} configured with the given options.
     */
    This multi(PublishOptions options);
}
