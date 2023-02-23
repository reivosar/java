package reivosar.common.util.lang;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Constructor;

public class ConstructorUtil {
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private ConstructorUtil() {
        // This constructor must be private
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T newInstance(final Constructor<?> constructor, final Object... parameters) {
        ObjectUtil.requireNonNull("constructor", constructor);
        try {
            return (T) constructor.newInstance(ArrayUtils.nullToEmpty(parameters));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
