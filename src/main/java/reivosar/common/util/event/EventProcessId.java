package reivosar.common.util.event;

import reivosar.common.util.model.Model;

import java.util.UUID;

/**
 * A class representing an identifier for an event process. This identifier is used to track the processing status of an event.
 */
public class EventProcessId extends Model {
    
    private final UUID uuid;
    
    /**
     * Constructs a new EventProcessId with a randomly generated UUID.
     */
    EventProcessId() {
        this.uuid = UUID.randomUUID();
    }
    
    /**
     * Returns the string representation of the UUID.
     *
     * @return the string representation of the UUID.
     */
    public String asString() {
        return uuid.toString();
    }
}