package reivosar.common.util.event;

import reivosar.common.util.promise.Promise;

/**
 * A publisher for events.
 */
public interface EventPublisher {
    
    /**
     * Publishes an event.
     *
     * @param event the event to publish
     * @return a promise that completes when the event has been published
     */
    Promise<Void> publishEvent(Event event);
}