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