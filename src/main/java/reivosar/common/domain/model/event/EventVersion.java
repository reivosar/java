package reivosar.common.domain.model.event;

import reivosar.common.domain.model.ValueObject;

/**
 * A value object representing the version of an event.
 */
public class EventVersion extends ValueObject<EventVersion> {
    
    /**
     * The version value.
     */
    final Integer value;
    
    /**
     * Constructs an event version with a default value of 1.
     */
    public EventVersion() {
        this(1);
    }
    
    /**
     * Constructs an event version with the specified value.
     *
     * @param value the version value
     */
    public EventVersion(int value) {
        this.value = value;
    }
}
