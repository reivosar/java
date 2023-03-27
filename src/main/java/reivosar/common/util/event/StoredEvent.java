package reivosar.common.util.event;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * An interface that represents a stored event in the event store.
 */
public interface StoredEvent extends Serializable {
    
    /**
     * Returns the unique ID of the stored event.
     *
     * @return the unique ID of the stored event
     */
    StoredEventId getStoredEventId();
    
    /**
     * Returns the event that was stored.
     *
     * @return the event that was stored
     */
    Event getEvent();
    
    /**
     * Returns the date and time that the event was stored.
     *
     * @return the date and time that the event was stored
     */
    LocalDateTime getEventStoredDateTime();
}