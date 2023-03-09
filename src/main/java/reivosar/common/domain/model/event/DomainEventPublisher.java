package reivosar.common.domain.model.event;

import reivosar.common.util.promise.Promise;

import java.util.Collection;

/**
 * DomainEventPublisher is an interface for publishing domain events asynchronously.
 */
public interface DomainEventPublisher {
    
    /**
     * Publishes the specified domain event asynchronously.
     *
     * @param event the domain event to be published
     * @return a promise object
     */
    Promise<?> publish(Event event);
    
    /**
     * Publishes the specified collection of domain events asynchronously.
     *
     * @param events the collection of domain events to be published
     * @return a promise object
     */
    Promise<?> publishAll(Collection<Event> events);
}



