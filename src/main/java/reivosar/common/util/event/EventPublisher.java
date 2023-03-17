package reivosar.common.util.event;

import reivosar.common.util.promise.Promise;

import java.util.Collection;
import java.util.Collections;

/**
 * An interface for classes that can publish events.
 */
@FunctionalInterface
public interface EventPublisher {
    
    /**
     * Publishes a single event.
     *
     * @param event the event to publish
     * @return a promise that resolves when the event has been published
     */
    default Promise<Void> publish(Event event) {
        return publish(Collections.singletonList(event));
    }
    
    /**
     * Publishes a collection of events.
     *
     * @param events the events to publish
     * @return a promise that resolves when all events have been published
     */
    Promise<Void> publish(Collection<Event> events);
    
    /**
     * Returns an instance of the default event publisher.
     *
     * @return an instance of the default event publisher
     */
    static EventPublisher instance() {
        return EventPublisherFactory.createDefaultPublisher();
    }
}