package reivosar.common.lang.reflect.type;

import java.util.Collection;
import java.util.Map;

/**
 * An interface for type descriptors, providing information about a field or method's type.
 */
public interface TypeDescriptor {
    
    /**
     * Returns the name of the type.
     *
     * @return the name of the type
     */
    String getName();
    
    /**
     * Returns the raw type of the type.
     *
     * @return the raw type of the type
     */
    Class<?> getRawType();
    
    /**
     * Returns {@code true} if the type is a primitive type, {@code false} otherwise.
     *
     * @return {@code true} if the type is a primitive type, {@code false} otherwise
     */
    default boolean isPrimitive() {
        return getRawType().isPrimitive();
    }
    
    /**
     * Returns {@code true} if the type is an array type, {@code false} otherwise.
     *
     * @return {@code true} if the type is an array type, {@code false} otherwise
     */
    default boolean isArray() {
        return getRawType().isArray();
    }
    
    /**
     * Returns {@code true} if the type is a collection type, {@code false} otherwise.
     *
     * @return {@code true} if the type is a collection type, {@code false} otherwise
     */
    default boolean isCollection() {
        return Collection.class.isAssignableFrom(getRawType());
    }
    
    /**
     * Returns {@code true} if the type is a map type, {@code false} otherwise.
     *
     * @return {@code true} if the type is a map type, {@code false} otherwise
     */
    default boolean isMap() {
        return Map.class.isAssignableFrom(getRawType());
    }
}
