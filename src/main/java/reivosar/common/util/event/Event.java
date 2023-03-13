package reivosar.common.util.event;

import java.time.LocalDateTime;

/**
 * The base interface for events.
 * <p>
 * Implementations of this interface should provide a timestamp for when the event occurred.
 */
public interface Event {
    
    /**
     * Gets the date and time when the event occurred.
     *
     * @return the date and time when the event occurred
     */
    LocalDateTime occurredOn();
}

