package reivosar.common.domain.model.event;

import reivosar.common.domain.model.Identity;

/**
 * This class represents the identity of an event. It extends the Identity class and uses a UUID-based string value
 * as the event ID.
 */
public class EventId extends Identity<EventId> {
    
    /**
     * The UUID-based string value of the event ID.
     */
    final String value;
    
    /**
     * Creates a new EventId instance with a new UUID-based string value as the event ID.
     */
    public EventId() {
        this.value = generateNativeIdByUUID();
    }
    
    /**
     * Returns the UUID-based string value of the event ID.
     *
     * @return the UUID-based string value of the event ID.
     */
    public String getValue() {
        return value;
    }
}
