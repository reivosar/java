package reivosar.common.lang;

import org.apache.commons.lang3.ArrayUtils;

/**
 * A utility class that provides array manipulation methods.
 */
public final class ArrayUtil {
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ArrayUtil() {
        // This constructor must be private
    }
    
    /**
     * Checks if the specified array is empty.
     *
     * @param array the array to check
     * @return true if the array is null or has zero length, false otherwise
     */
    public static boolean isEmpty(final Object... array) {
        return array == null || array.length == 0;
    }
    
    /**
     * Checks if the specified array is not empty.
     *
     * @param array the array to check
     * @return true if the array is not null and has a positive length, false otherwise
     */
    public static boolean isNotEmpty(final Object... array) {
        return !isEmpty(array);
    }
    
    /**
     * Replaces a {@code null} array with an empty array, or returns the non-{@code null} array as is.
     *
     * @param array the array to replace if {@code null}
     * @return an empty array if {@code array} is {@code null}, or the non-{@code null} array as is
     */
    public static Object[] nullToEmpty(final Object... array) {
        if (array == null) {
            return ArrayUtils.EMPTY_OBJECT_ARRAY;
        }
        return ArrayUtils.nullToEmpty(array);
    }
}
