package reivosar.common.domain.model.event;

import reivosar.common.domain.model.Identity;
import reivosar.common.util.promise.Promise;

/**
 * A domain event publisher interface that allows for asynchronous publishing of events
 * <p>
 * and waiting for their completion using promises.
 *
 * @param <ID>     the type of identity used for the entity
 * @param <ENTITY> the type of entity that can publish events
 */
public interface DomainEventPublisher<ID extends Identity<ID>, ENTITY extends EventPublishableEntity<ID, ENTITY>> {
    
    /**
     * Asynchronously publishes an eventable entity.
     *
     * @param entity the entity to publish
     */
    void asyncPublish(ENTITY entity);
    
    /**
     * Asynchronously publishes an eventable entity and returns a promise for the result.
     *
     * @param entity the entity to publish
     * @return a promise that will be resolved when the event is published
     */
    Promise<Object> awaitPublish(ENTITY entity);
}



