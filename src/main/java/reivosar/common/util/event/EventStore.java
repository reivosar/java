package reivosar.common.util.event;

import java.util.Collection;
import java.util.Optional;

/**
 * An interface for an event store that can add, retrieve, and remove events.
 */
public interface EventStore {
    
    /**
     * Adds an event to the store.
     *
     * @param event the event to add
     * @return true if this store changed as a result of the call, false otherwise
     */
    boolean add(Event event);
    
    /**
     * Retrieves the next event in the store.
     *
     * @return an optional containing the next event, or an empty optional if there are no more events
     */
    Optional<Event> nextEvent();
    
    /**
     * Checks if there is at least one event in the store.
     *
     * @return true if there is at least one event, false otherwise
     */
    default boolean hasEvent() {
        return nextEvent().isPresent();
    }
    
    /**
     * Removes an event from the store.
     *
     * @param event the event to remove
     */
    void remove(Event event);
    
    /**
     * Retrieves all events in the store.
     *
     * @return a collection of all events in the store
     */
    Collection<Event> getAll();
    
    /**
     * Clears all events from the store.
     */
    void clear();
}