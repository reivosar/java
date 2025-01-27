package reivosar.common.async.event;

import java.util.Collection;
import java.util.Optional;

/**
 * Represents an event store that provides functionality for persisting and retrieving events.
 * <p>
 * Implementations of this interface are responsible for managing the lifecycle of events,
 * including creation, updates, and queries for specific events or their descriptors.
 *
 * @param <E> the type of event managed by this store, which extends the {@link Event} interface
 */
public interface EventStore<E extends Event> {

    /**
     * Creates a new event descriptor for the given event and stores it in the event store.
     *
     * @param event the event to create and store; must not be {@code null}
     * @return {@code true} if the event was successfully created and stored, {@code false} otherwise
     * @throws NullPointerException if {@code event} is {@code null}
     */
    boolean create(E event);

    /**
     * Updates the given event descriptor in the event store.
     *
     * @param event the event descriptor to update; must not be {@code null}
     * @return {@code true} if the event descriptor was successfully updated, {@code false} otherwise
     * @throws NullPointerException if {@code event} is {@code null}
     */
    boolean update(EventDescriptor<E> event);

    /**
     * Finds and returns an event descriptor with the given identifier, if it exists in the event store.
     *
     * @param eventDescriptorIdentify the identifier of the event descriptor to find; must not be {@code null}
     * @return an {@link Optional} containing the event descriptor with the given identifier,
     *         or {@link Optional#empty()} if it does not exist
     * @throws NullPointerException if {@code eventDescriptorIdentify} is {@code null}
     */
    Optional<EventDescriptor<E>> findById(EventDescriptorIdentify eventDescriptorIdentify);

    /**
     * Checks whether there are any uncompleted events in the event store.
     * <p>
     * This is a convenience method that checks if the result of {@link #getUncompletedEvents()} is not empty.
     *
     * @return {@code true} if there are uncompleted events, {@code false} otherwise
     */
    default boolean hasUncompletedEvent() {
        return !getUncompletedEvents().isEmpty();
    }

    /**
     * Returns a collection of all uncompleted event descriptors in the event store.
     * <p>
     * An uncompleted event descriptor is one that has not been marked as completed
     * (e.g., through a status or completion timestamp).
     *
     * @return a {@link Collection} of all uncompleted event descriptors; never {@code null}, but may be empty
     */
    Collection<EventDescriptor<E>> getUncompletedEvents();
}
