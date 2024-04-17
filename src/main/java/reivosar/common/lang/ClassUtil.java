package reivosar.common.lang;

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
     * Determines if all classes in the first collection are assignable to their corresponding classes in the second collection,
     * based on Java's type inheritance rules.
     *
     * @param froms            a Collection of Class objects representing the classes to check for assignability
     * @param tos              a Collection of Class objects representing the classes that froms will be assigned to
     * @param strictComparison if true, only an exact match will be considered assignable, otherwise inheritance will be considered
     * @return true if all classes in froms are assignable to their corresponding classes in tos, false otherwise
     * @throws NullPointerException if either froms or tos is null
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
     * Determines if a Class object is assignable toClass another Class object, based on Java's type inheritance rules.
     *
     * @param fromCLass             a Class object representing the class toClass check for assignability
     * @param toClass               a Class object representing the class that fromCLass will be assigned toClass
     * @param strictComparison if true, only an exact match will be considered assignable, otherwise inheritance will be considered
     * @return true if fromCLass is assignable to toClass, false otherwise
     * @throws NullPointerException if either fromCLass or toClass is null
     */
    public static boolean isAssignable(final Class<?> fromCLass, final Class<?> toClass, boolean strictComparison) {
        ObjectUtil.requireNonNull("fromCLass", fromCLass);
        ObjectUtil.requireNonNull("toClass", toClass);
        if (strictComparison) {
            return fromCLass.equals(toClass);
        }
        if (toClass == Object.class) {
            return false;
        }
        return ClassUtils.isAssignable(fromCLass, toClass, true);
    }
}