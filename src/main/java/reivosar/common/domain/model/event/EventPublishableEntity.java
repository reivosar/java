package reivosar.common.domain.model.event;

import reivosar.common.domain.model.Entity;
import reivosar.common.domain.model.Identity;

import java.util.Collection;

/**
 * An abstract class representing an entity that can publish events.
 *
 * @param <ID>     the type of identity used for the entity
 * @param <ENTITY> the type of the entity itself
 */
@SuppressWarnings("unchecked")
public abstract class EventPublishableEntity<ID extends Identity<ID>, ENTITY> extends Entity<ID, ENTITY> {
    
    private final EventStore store;
    
    protected EventPublishableEntity() {
        this.store = new EventStore();
    }
    
    /**
     * Applies a series of domain events to the entity and stores them in the event store.
     *
     * @param domainEvents the domain events to apply
     * @param <R>          the type of the entity
     * @return the entity with the applied events
     */
    protected <R extends EventPublishableEntity<ID, ENTITY>> R apply(final Event... domainEvents) {
        this.store.eventOccurred(domainEvents);
        return (R) this;
    }
    
    /**
     * Gets all events that have occurred on the entity.
     *
     * @return a collection of all events
     */
    public Collection<Event> allEvents() {
        return store.allEvents();
    }
    
    /**
     * Gets all events that have occurred on the entity with the given version number.
     *
     * @param eventVersion the version number of the events to retrieve
     * @return a collection of events with the given version number
     */
    public Collection<Event> allEvents(final EventVersion eventVersion) {
        return store.allEvents(eventVersion);
    }
    
    /**
     * Clears all events from the event store.
     *
     * @param <R> the type of the entity
     * @return the entity with no events
     */
    public <R extends EventPublishableEntity<ID, ENTITY>> R clear() {
        this.store.clear();
        return (R) this;
    }
    
    /**
     * Determines if the entity has any events in the event store.
     *
     * @return true if the entity has events, false otherwise
     */
    public boolean hasEvents() {
        return store.hasEvents();
    }
}





