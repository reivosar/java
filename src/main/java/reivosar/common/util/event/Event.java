package reivosar.common.util.event;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The interface for events in the event-driven architecture.
 *
 * <p>
 * This interface extends the Serializable interface, allowing the implementing class to be serialized and
 * <p>
 * transmitted over a network or stored in a file.
 */
public interface Event extends Serializable {
    
    /**
     * Returns the priority of this event.
     *
     * @return the priority of this event
     */
    default EventPriority eventPriority() {
        return EventPriority.NORMAL;
    }
}

