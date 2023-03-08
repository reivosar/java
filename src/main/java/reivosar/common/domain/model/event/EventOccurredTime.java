package reivosar.common.domain.model.event;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.domain.model.time.DateTime;

/**
 * A class representing the time when an event occurred.
 */
public class EventOccurredTime extends ValueObject<EventOccurredTime> {
    
    final DateTime value;
    
    public EventOccurredTime() {
        this(DateTime.now());
    }
    
    public EventOccurredTime(DateTime value) {
        this.value = value;
    }
    
    /**
     * Gets the value of the event occurred time.
     *
     * @return the value of the event occurred time
     */
    public DateTime getValue() {
        return value;
    }
}