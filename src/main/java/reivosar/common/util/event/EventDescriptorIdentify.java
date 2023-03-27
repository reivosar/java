package reivosar.common.util.event;

import java.io.Serializable;

/**
 * This functional interface represents the identity of an {@link EventDescriptor}.
 * <p>
 * It provides a method to obtain the identity in string format, and a default implementation to compare
 * <p>
 * two identities.
 */
@FunctionalInterface
public interface EventDescriptorIdentify extends Serializable {
    
    /**
     * Returns the identity in string format.
     *
     * @return the identity in string format
     */
    String asString();
    
    /**
     * Compares this identity with another one to determine if they are the same.
     * Returns true if they are the same, false otherwise. If the passed identity is null, returns false.
     *
     * @param eventDescriptorIdentify the identity to compare with
     * @return true if they are the same, false otherwise
     */
    default boolean sameIdentifyAs(EventDescriptorIdentify eventDescriptorIdentify) {
        if (eventDescriptorIdentify == null) {
            return false;
        }
        return asString().equals(eventDescriptorIdentify.asString());
    }
}
