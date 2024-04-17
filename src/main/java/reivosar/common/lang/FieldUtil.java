package reivosar.common.lang;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.Collection;

/**
 * A utility class that provides methods for accessing and modifying field values.
 */
public final class FieldUtil {
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private FieldUtil() {
        // This constructor must be private
    }
    
    /**
     * Returns all fields declared in the given class and its superclasses.
     *
     * @param clazz the class to get the fields from
     * @return a collection of all fields declared in the given class and its superclasses
     * @see FieldUtils#getAllFieldsList(Class)
     */
    public static Collection<Field> getAllFields(final Class<?> clazz) {
        return FieldUtils.getAllFieldsList(clazz);
    }
    
    /**
     * Returns all fields declared in the given class and its superclasses as an array.
     *
     * @param clazz the class to get the fields from
     * @return an array of all fields declared in the given class and its superclasses
     * @see FieldUtils#getAllFieldsList(Class)
     */
    public static Field[] getAllFieldArray(final Class<?> clazz) {
        return getAllFields(clazz).toArray(new Field[0]);
    }
    
    /**
     * Returns the field with the given name declared in the given class or one of its superclasses.
     *
     * @param clazz     the class to get the field from
     * @param fieldName the name of the field to get
     * @return the field with the given name declared in the given class or one of its superclasses, or null if not found
     * @see FieldUtils#getDeclaredField(Class, String, boolean)
     */
    public static Field getDeclaredField(final Class<?> clazz, final String fieldName) {
        return FieldUtils.getDeclaredField(clazz, fieldName, true);
    }
    
    /**
     * Returns the value of the specified field on the given object.
     *
     * @param field  the field to read the value of
     * @param target the object to read the field from
     * @return the value of the field
     * @throws IllegalStateException if the field cannot be accessed or the target object is {@code null}
     */
    public static Object readField(final Field field, final Object target) {
        ObjectUtil.requireNonNull("field", field);
        ObjectUtil.requireNonNull("target", target);
        try {
            return FieldUtils.readField(field, target);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    /**
     * Returns the value of the specified static field.
     *
     * @param field the static field to read the value of
     * @return the value of the field
     * @throws IllegalStateException if the field cannot be accessed
     */
    public static Object readStaticField(final Field field) {
        ObjectUtil.requireNonNull("target", field);
        try {
            return FieldUtils.readStaticField(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    /**
     * Sets the value of the specified field on the given object.
     *
     * @param field  the field to set the value of
     * @param target the object to set the field value on
     * @param value  the new value for the field
     * @throws IllegalStateException if the field cannot be accessed or the target object is {@code null}
     */
    public static void writeField(final Field field, final Object target, final Object value) {
        ObjectUtil.requireNonNull("field", field);
        ObjectUtil.requireNonNull("target", target);
        try {
            FieldUtils.writeField(field, target, value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    /**
     * Sets the value of the specified static field.
     *
     * @param field the static field to set the value of
     * @param value the new value for the field
     * @throws IllegalStateException if the field cannot be accessed
     */
    public static void writeStaticField(final Field field, final Object value) {
        ObjectUtil.requireNonNull("field", field);
        try {
            FieldUtils.writeStaticField(field, value);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}
