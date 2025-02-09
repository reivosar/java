package reivosar.common.async.event;

import reivosar.common.async.options.AsyncOptions;
import reivosar.common.async.promise.Promise;
import reivosar.common.lang.ObjectUtil;
import reivosar.common.lang.function.VoidConsumer;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A template class for publishing events.
 * <p>
 * This abstract class provides a framework for event publishing by implementing the {@link EventPublisher} interface.
 * It defines common behavior for publishing events while allowing subclasses to specify the actual publishing mechanism.
 * </p>
 *
 * @param <E>    the type of event to be published, which extends the {@link Event} interface
 * @param <This> the concrete type of the publisher that extends this template
 */
public abstract class EventPublisherTemplate<E extends Event, This extends EventPublisher<E, This>>
        implements EventPublisher<E, This> {

    private final AsyncOptions options;

    /**
     * Creates an instance of {@code EventPublisherTemplate} with default publishing options.
     */
    protected EventPublisherTemplate() {
        this(AsyncOptions.builder().build());
    }

    /**
     * Creates an instance of {@code EventPublisherTemplate} with custom asynchronous processing options.
     *
     * @param options the {@link AsyncOptions} to use for event publishing behavior
     * @throws NullPointerException if {@code options} is {@code null}
     */
    protected EventPublisherTemplate(final AsyncOptions options) {
        this.options = ObjectUtil.requireNonNull("options", options);
    }

    /**
     * Configures the publisher for single (sequential) event processing.
     * <p>
     * This method allows the publisher to be configured for sequential processing with the specified {@link AsyncOptions}.
     * </p>
     *
     * @param options the {@link AsyncOptions} to apply
     * @return the current instance of the publisher
     */
    @SuppressWarnings("unchecked")
    @Override
    public This single(AsyncOptions options) {
        this.options.copyFrom(options);
        return (This) this;
    }

    /**
     * Configures the publisher for multiple (parallel) event processing.
     * <p>
     * This method allows the publisher to be configured for parallel processing with the specified {@link AsyncOptions}.
     * </p>
     *
     * @param options the {@link AsyncOptions} to apply
     * @return the current instance of the publisher
     */
    @SuppressWarnings("unchecked")
    @Override
    public This multi(AsyncOptions options) {
        this.options.copyFrom(options);
        return (This) this;
    }

    /**
     * Publishes a collection of events asynchronously.
     * <p>
     * This method takes a collection of events and processes them using a {@link Promise}.
     * Subclasses must implement the {@link #doPublishEvent(Event)} method to define the actual publishing logic.
     * </p>
     *
     * @param events the collection of events to be published; must not be {@code null} or empty
     * @return a {@link Promise} representing the asynchronous publishing process
     * @throws NullPointerException if {@code events} is {@code null} or empty
     */
    @Override
    public final Promise<Void> publish(final Collection<E> events) {
        ObjectUtil.requireNonNullAndEmpty("events", events);
        return Promise.all(
                events.stream()
                        .map(event -> (VoidConsumer) () -> doPublishEvent(event))
                        .collect(Collectors.toList())
        );
    }

    /**
     * Defines how a single event should be published.
     * <p>
     * This method must be implemented by subclasses to provide the actual event publishing logic.
     * </p>
     *
     * @param event the event to be published; must not be {@code null}
     * @throws NullPointerException if {@code event} is {@code null}
     */
    protected abstract void doPublishEvent(final E event);
}
