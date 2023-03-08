package reivosar.common.domain.model.event;

import reivosar.common.domain.model.Identity;

/**
 * A class representing the unique identity of an event.
 */
public class EventId extends Identity<EventId> {
    
    final String value;
    
    public EventId() {
        this.value = generateNativeIdByUUID();
    }
    
    /**
     * Gets the value of the event ID.
     *
     * @return the value of the event ID
     */
    public String getValue() {
        return value;
    }
}