package reivosar.common.async.event;

import reivosar.common.async.promise.Promise;
import reivosar.common.lang.function.VoidConsumer;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A template class for publishing events.
 * <p>
 * This abstract class provides a framework for event publishing by implementing the {@link EventPublisher} interface.
 * Subclasses should implement the {@link #doPublishEvent(EventConfig, Event)} method to define how an event
 * is published based on a specific configuration.
 *
 * @param <E> the type of event to be published, which extends the {@link Event} interface
 */
public abstract class EventPublisherTemplate<E extends Event> implements EventPublisher<E> {

    private final EventConfig<E> eventConfig;

    /**
     * Creates a new instance of {@code EventPublisherTemplate}.
     *
     * @param eventConfig the event configuration to be used for publishing events
     * @throws IllegalArgumentException if {@code eventConfig} is null
     */
    protected EventPublisherTemplate(final EventConfig<E> eventConfig) {
        if (eventConfig == null) {
            throw new IllegalArgumentException("EventConfig cannot be null");
        }
        this.eventConfig = eventConfig;
    }

    /**
     * Publishes a collection of events asynchronously.
     * <p>
     * This method takes a collection of events and publishes them using a {@link Promise}.
     * Subclasses should define the actual publishing logic by overriding the {@link #doPublishEvent(EventConfig, Event)} method.
     *
     * @param events the collection of events to be published
     * @return a {@link Promise} representing the asynchronous publishing process
     * @throws NullPointerException if {@code events} is null
     */
    @Override
    public final Promise<Void> publish(final Collection<E> events) {
        if (events == null) {
            throw new NullPointerException("Events collection cannot be null");
        }
        return Promise.all(
                events.stream()
                        .map(event -> (VoidConsumer) () ->
                                doPublishEvent(eventConfig, event)
                        )
                        .collect(Collectors.toList())
        );
    }

    /**
     * Publishes a single event.
     * <p>
     * This method must be implemented by subclasses to provide the actual event publishing logic.
     *
     * @param eventConfig the configuration for publishing the event
     * @param event       the event to be published
     * @throws NullPointerException if {@code eventConfig} or {@code event} is null
     */
    protected abstract void doPublishEvent(final EventConfig<E> eventConfig, final E event);
}
