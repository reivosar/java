package reivosar.common.async.event;

import reivosar.common.async.promise.Promise;

import java.util.Collection;
import java.util.Collections;

/**
 * Represents a publisher responsible for publishing events.
 * <p>
 * This functional interface provides methods to publish single or multiple events
 * asynchronously, returning a {@link Promise} to handle the completion of the publishing process.
 *
 * @param <E> the type of event to be published, which extends the {@link Event} interface
 */
@FunctionalInterface
public interface EventPublisher<E extends Event> {

    /**
     * Publishes a single event.
     * <p>
     * This method is a convenience wrapper for the {@link #publish(Collection)} method, which
     * allows publishing a single event by internally wrapping it in a {@link Collections#singletonList(Object)}.
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
     * This method handles the logic to publish multiple events asynchronously.
     * Implementations should define how events are dispatched, processed, or stored.
     *
     * @param events the collection of events to publish; must not be {@code null} or contain {@code null} elements
     * @return a {@link Promise} that resolves when all events have been successfully published
     * @throws NullPointerException if {@code events} is {@code null} or contains {@code null} elements
     */
    Promise<Void> publish(Collection<E> events);
}
