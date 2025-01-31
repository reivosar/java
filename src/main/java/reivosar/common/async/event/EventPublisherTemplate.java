package reivosar.common.async.event;

import reivosar.common.async.promise.Promise;
import reivosar.common.lang.ObjectUtil;
import reivosar.common.lang.function.VoidConsumer;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * A template class for publishing events.
 * <p>
 * This abstract class provides a framework for event publishing by implementing the {@link EventPublisher} interface.
 * Subclasses should implement the {@link #doPublishEvent(Event)} method to define how an event
 * is published based on a specific configuration.
 *
 * @param <E> the type of event to be published, which extends the {@link Event} interface
 */
public abstract class EventPublisherTemplate<E extends Event> implements EventPublisher<E> {

    /**
     * Publishes a collection of events asynchronously.
     * <p>
     * This method takes a collection of events and publishes them using a {@link Promise}.
     * Subclasses should define the actual publishing logic by overriding the {@link #doPublishEvent(Event)} method.
     *
     * @param events the collection of events to be published
     * @return a {@link Promise} representing the asynchronous publishing process
     * @throws NullPointerException if {@code events} is null
     */
    @Override
    public final Promise<Void> publish(final Collection<E> events) {
        ObjectUtil.requireNonNullAndEmpty("events", events);
        return Promise.all(
                events.stream()
                        .map(event -> (VoidConsumer) () ->
                                doPublishEvent(event)
                        )
                        .collect(Collectors.toList())
        );
    }

    /**
     * Publishes a single event.
     * <p>
     * This method must be implemented by subclasses to provide the actual event publishing logic.
     *
     * @param event the event to be published
     * @throws NullPointerException if {@code eventConfig} or {@code event} is null
     */
    protected abstract void doPublishEvent(final E event);
}
