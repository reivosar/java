package reivosar.common.util.lang;

import org.apache.commons.lang3.ClassUtils;

import java.util.ArrayList;
import java.util.Collection;

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
    
    
    /**
     * Determines whether the types in a source collection are assignable to the
     * corresponding types in a target collection. If strict comparison is used,
     * the types must match exactly. Otherwise, the method will attempt to determine
     * whether the source types are assignable to the target types, taking into
     * account parameterized types and inheritance.
     *
     * @param froms the source collection of types to be checked
     * @param tos the target collection of types to be checked against
     * @param strictComparison whether strict comparison is to be used
     * @return true if the types in the source collection are assignable to the types
     *         in the target collection; false otherwise
     */
    public static boolean isAssignable(final Collection<Class<?>> froms,
                                       final Collection<Class<?>> tos,
                                       boolean strictComparison) {
        if (froms == null || tos == null) {
            return froms == tos;
        }
        if (froms.size() != tos.size()) {
            return false;
        }
        final ArrayList<Class<?>> arrayListA = new ArrayList<>(froms);
        final ArrayList<Class<?>> arrayListB = new ArrayList<>(tos);
        for (int i = 0; i < arrayListA.size(); i++) {
            if (!isAssignable(arrayListA.get(i), arrayListB.get(i), strictComparison)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Determines whether a from type is assignable to a to type. If strict
     * comparison is used, the types must match exactly. Otherwise, the method will
     * attempt to determine whether the from type is assignable to the to type,
     * taking into account parameterized types and inheritance.
     *
     * @param from the from type to be checked
     * @param to the to type to be checked against
     * @param strictComparison whether strict comparison is to be used
     * @return true if the from type is assignable to the to type; false otherwise
     * @throws NullPointerException if either the from or to type is null
     */
    public static boolean isAssignable(final Class<?> from, final Class<?> to, boolean strictComparison) {
        ObjectUtil.requireNonNull("From", from);
        ObjectUtil.requireNonNull("To", to);
        if (strictComparison) {
            return from.equals(to);
        }
        if (to == Object.class) {
            return false;
        }
        return ClassUtils.isAssignable(from, to, true);
    }
}