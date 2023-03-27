package reivosar.common.util.event;

import java.io.Serializable;

/**
 * A functional interface for representing a stored event ID.
 */
@FunctionalInterface
public interface StoredEventId extends Serializable {
    
    /**
     * Returns the ID as a string.
     *
     * @return the ID as a string
     */
    String asString();
}
