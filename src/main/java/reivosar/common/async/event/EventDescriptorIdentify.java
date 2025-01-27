package reivosar.common.async.event;

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
}
