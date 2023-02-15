package reivosar.common.util.lang;

import org.apache.commons.lang3.ObjectUtils;

/**
 * The ObjectUtil class provides utility methods for working with Java Objects.
 */
public class ObjectUtil {
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ObjectUtil() {
        // This constructor should be private
    }
    
    /**
     * Returns whether the specified object is empty or null.
     *
     * @param object the object to check for emptiness
     * @return true if the object is empty or null, false otherwise
     */
    public static boolean isEmpty(final Object object) {
        return ObjectUtils.isEmpty(object);
    }
    
    /**
     * Returns whether the specified object is not empty or null.
     *
     * @param object the object to check for non-emptiness
     * @return true if the object is not empty or null, false otherwise
     */
    public static boolean isNotEmpty(final Object object) {
        return !isEmpty(object);
    }
    
    /**
     * Checks that the specified object is not null and throws a NullPointerException
     * if it is null.
     *
     * @param name   the name of the object being checked (used in the exception message)
     * @param object the object to check for nullness
     * @param <T>    the type of the object
     * @return the object if it is not null
     * @throws NullPointerException if the object is null
     */
    public static <T> T requireNonNull(final String name, final T object) {
        if (object == null)
            throw new NullPointerException(name + " must not be null");
        return object;
    }
    
    /**
     * Returns the specified object if it is not null, or the default object if it is null.
     *
     * @param object        the object to check for nullness
     * @param defaultObject the default object to return if the specified object is null
     * @param <T>           the type of the objects
     * @return the specified object if it is not null, or the default object if it is null
     */
    public static <T> T getIfNull(final T object, final T defaultObject) {
        return ObjectUtils.defaultIfNull(object, defaultObject);
    }
}
