package reivosar.common.util.lang;

import org.apache.commons.lang3.ClassUtils;

/**
 * The ClassUtil class provides utility methods for working with classes.
 */
public final class ClassUtil {
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ClassUtil() {
        // This constructor must be private
    }
    
    /**
     * Returns the package name of the given {@code Class} object.
     *
     * @param cls the {@code Class} object to get the package name from
     * @return the package name of the given {@code Class} object
     */
    public static String getPackageName(final Class<?> cls) {
        return ClassUtils.getPackageName(ObjectUtil.requireNonNull("cls", cls));
    }
    
    /**
     * Returns the simple name of the given {@code Class} object.
     *
     * @param cls the {@code Class} object to get the simple name from
     * @return the simple name of the given {@code Class} object
     */
    public static String getSimpleName(final Class<?> cls) {
        return ClassUtils.getSimpleName(ObjectUtil.requireNonNull("cls", cls));
    }
    
    /**
     * Converts a primitive Class instance to its corresponding wrapper Class instance.
     *
     * @param cls the class to convert, may be {@code null}
     * @return the wrapper class for {@code cls} or {@code cls} if {@code cls} is not a primitive class
     * @see ClassUtils#primitiveToWrapper(Class)
     */
    public static Class<?> primitiveToWrapper(final Class<?> cls) {
        return ClassUtils.primitiveToWrapper(cls);
    }
    
    /**
     * Converts the specified array of objects to an array of corresponding classes.
     *
     * @param array the array of objects to be converted
     * @return an array of corresponding classes
     */
    public static Class<?>[] toClass(final Object... array) {
        return ClassUtils.toClass(ArrayUtil.nullToEmpty(array));
    }
}