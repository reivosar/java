package reivosar.common.domain.model.event;


import reivosar.common.domain.model.ValueObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A class representing a store for events.
 */
public class EventStore extends ValueObject<EventStore> {
    
    private final Collection<Event> events;
    
    public EventStore() {
        this.events = new ConcurrentLinkedQueue<>();
    }
    
    /**
     * Adds one or more events to the event store.
     *
     * @param domainEvents the events to add
     */
    public void eventOccurred(Event... domainEvents) {
        this.events.addAll(Arrays.asList(domainEvents));
    }
    
    /**
     * Gets all events in the event store.
     *
     * @return a collection of all events
     */
    public Collection<Event> allEvents() {
        return Collections.unmodifiableCollection(events);
    }
    
    /**
     * Gets all events in the event store with the specified event version.
     *
     * @param eventVersion the event version to filter by
     * @return a collection of all events with the specified event version
     */
    public Collection<Event> allEvents(EventVersion eventVersion) {
        return allEvents().stream()
                .filter(event -> eventVersion.value.equals(event.getEventVersion())).toList();
    }
    
    /**
     * Clears all events from the event store.
     */
    public void clear() {
        this.events.clear();
    }
    
    /**
     * Checks if the event store has any events.
     *
     * @return true if the event store has events, false otherwise
     */
    public boolean hasEvents() {
        return !events.isEmpty();
    }
}