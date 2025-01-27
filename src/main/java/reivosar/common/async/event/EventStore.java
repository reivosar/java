package reivosar.common.async.event;

import java.util.Collection;
import java.util.Optional;

/**
 * An interface representing an event store that can be used to persist and retrieve events.
 */
public interface EventStore {
    
    /**
     * Creates a new event descriptor and stores it in the event store.
     *
     * @param event the event to create and store
     * @return true if the event was successfully created and stored, false otherwise
     */
    boolean create(Event event);
    
    /**
     * Updates the given event descriptor in the event store.
     *
     * @param event the event descriptor to update
     * @return true if the event descriptor was successfully updated, false otherwise
     */
    boolean update(EventDescriptor event);
    
    /**
     * Finds and returns an event descriptor with the given identifier, if it exists in the event store.
     *
     * @param eventDescriptorIdentify the identifier of the event descriptor to find
     * @return an optional containing the event descriptor with the given identifier, or empty if it does not exist
     */
    Optional<EventDescriptor> findById(EventDescriptorIdentify eventDescriptorIdentify);

    /**
     * Checks whether there are any uncompleted events in the event store.
     *
     * @return true if there are uncompleted events, false otherwise
     */
    default boolean hasUncompletedEvent() {
        return !getUncompletedEvents().isEmpty();
    }
    
    /**
     * Returns a collection of all uncompleted event descriptors in the event store.
     *
     * @return a collection of all uncompleted event descriptors
     */
    Collection<EventDescriptor> getUncompletedEvents();
}