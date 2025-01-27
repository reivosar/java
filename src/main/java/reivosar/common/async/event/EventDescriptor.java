package reivosar.common.async.event;

import java.time.Instant;
import java.util.Optional;

/**
 * A descriptor for an event that provides metadata and state information about the event.
 * <p>
 * This interface defines methods for accessing details such as the identity of the event descriptor,
 * the associated event, and the timestamps related to the event's storage, publication, and completion.
 *
 * @param <E> the type of the event being described, which extends the {@link Event} interface
 */
public interface EventDescriptor<E extends Event> {

    /**
     * Returns the identity of this event descriptor.
     *
     * @return the {@link EventDescriptorIdentify} representing the unique identity of this event descriptor
     */
    EventDescriptorIdentify getEventDescriptorIdentify();

    /**
     * Checks whether this event descriptor has the same event as the given event descriptor.
     *
     * @param eventDescriptor the event descriptor to compare with
     * @return {@code true} if this event descriptor has the same event as the given event descriptor, {@code false} otherwise
     */
    default boolean isSameEvent(EventDescriptor<E> eventDescriptor) {
        return getEventDescriptorIdentify().equals(eventDescriptor.getEventDescriptorIdentify());
    }

    /**
     * Returns the event associated with this descriptor.
     *
     * @return the event of type {@code E} associated with this descriptor
     */
    E getEvent();

    /**
     * Returns the timestamp at which this event was stored.
     *
     * @return the {@link Instant} representing the time this event was stored
     */
    Instant getStoredOn();

    /**
     * Returns the timestamp at which this event was published, if available.
     *
     * @return an {@link Optional} containing the {@link Instant} of when this event was published,
     *         or {@link Optional#empty()} if the event has not been published
     */
    default Optional<Instant> getPublishedOn() {
        return Optional.empty();
    }

    /**
     * Checks whether this event has been completed.
     *
     * @return {@code true} if this event has been completed, {@code false} otherwise
     */
    default boolean isCompleted() {
        return getCompletedOn().isPresent();
    }

    /**
     * Returns the timestamp at which this event was completed, if available.
     *
     * @return an {@link Optional} containing the {@link Instant} of when this event was completed,
     *         or {@link Optional#empty()} if the event has not been completed
     */
    default Optional<Instant> getCompletedOn() {
        return Optional.empty();
    }
}
