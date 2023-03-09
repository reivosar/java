package reivosar.common.domain.model.event;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.domain.model.time.DateTime;

/**
 * A value object representing the time when an event occurred.
 */
public class EventOccurredTime extends ValueObject<EventOccurredTime> {
    
    /**
     * The actual date and time value of this event occurred time.
     */
    final DateTime value;
    
    /**
     * Creates a new `EventOccurredTime` instance representing the current date and time.
     */
    public EventOccurredTime() {
        this(DateTime.now());
    }
    
    /**
     * Creates a new `EventOccurredTime` instance with the specified date and time value.
     *
     * @param value The date and time value for this event occurred time.
     */
    public EventOccurredTime(final DateTime value) {
        this.value = value;
    }
    
    /**
     * Gets the date and time value of this event occurred time.
     *
     * @return The date and time value of this event occurred time.
     */
    public DateTime getValue() {
        return value;
    }
}
