package reivosar.common.util.lang;

import java.lang.reflect.Constructor;

/**
 * The ConstructorUtil class provides utility methods for working with constructors.
 */
public class ConstructorUtil {
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ConstructorUtil() {
        // This constructor must be private
    }
    
    /**
     * Creates a new instance of the class represented by the specified constructor, using the specified parameters.
     *
     * @param <T>         the type of the class for creating instance
     * @param constructor the constructor object
     * @param parameters  the parameters to be passed to the constructor
     * @return a new instance of the class represented by the constructor
     * @throws IllegalStateException if an error occurs while creating the new instance
     */
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(final Constructor<?> constructor, final Object... parameters) {
        ObjectUtil.requireNonNull("constructor", constructor);
        try {
            return (T) constructor.newInstance(ArrayUtil.nullToEmpty(parameters));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}