package reivosar.common.domain.model.event;

import java.util.Collection;

/**
 * An interface representing a repository for storing events.
 */
public interface EventRepository {
    
    /**
     * Generates a new unique identifier for an event.
     *
     * @return the new event ID
     */
    EventId generateId();
    
    /**
     * Saves a single event to the repository.
     *
     * @param event the event to save
     */
    void save(Event event);
    
    /**
     * Saves a collection of events to the repository.
     *
     * @param events the events to save
     */
    void saveAll(Collection<Event> events);
}