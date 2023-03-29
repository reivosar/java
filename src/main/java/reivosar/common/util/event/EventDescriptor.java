package reivosar.common.util.event;

import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

/**
 * A descriptor for an event.
 */
public interface EventDescriptor extends Serializable {
    
    /**
     * Returns the identity of this event descriptor.
     *
     * @return the identity of this event descriptor
     */
    EventDescriptorIdentify getEventDescriptorIdentify();
    
    /**
     * Checks whether this event descriptor has the same event as the given event descriptor.
     *
     * @param eventDescriptor the event descriptor to compare with
     * @return true if this event descriptor has the same event as the given event descriptor, false otherwise
     */
    default boolean isSameEvent(EventDescriptor eventDescriptor) {
        return getEventDescriptorIdentify().equals(eventDescriptor.getEventDescriptorIdentify());
    }
    
    /**
     * Returns the event of this descriptor.
     *
     * @return the event of this descriptor
     */
    Event getEvent();
    
    /**
     * Returns the instant at which this event was stored.
     *
     * @return the instant at which this event was stored
     */
    Instant getStoredOn();
    
    /**
     * Returns whether this event has been published.
     *
     * @return true if this event has been published, false otherwise
     */
    default boolean isPublished() {
        return getPublishedOn().isPresent();
    }
    
    /**
     * Returns the instant at which this event was published.
     *
     * @return the instant at which this event was published, or empty if not published
     */
    default Optional<Instant> getPublishedOn() {
        return Optional.empty();
    }
    
    /**
     * Returns whether this event has been completed.
     *
     * @return true if this event has been completed, false otherwise
     */
    default boolean isCompleted() {
        return getCompletedOn().isPresent();
    }
    
    /**
     * Returns the instant at which this event was completed.
     *
     * @return the instant at which this event was completed, or empty if not completed
     */
    default Optional<Instant> getCompletedOn() {
        return Optional.empty();
    }
    
}