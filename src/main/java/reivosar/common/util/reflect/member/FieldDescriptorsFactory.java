package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Field;

/**
 * This class provides a factory field to create a collection of field descriptors for a given class.
 */
public class FieldDescriptorsFactory {
    
    /**
     * Returns a collection of field descriptors for the specified class.
     *
     * @param aClass the class for which to create field descriptors.
     * @return a new collection of field descriptors for the specified class.
     */
    public static FieldDescriptors createDescriptors(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        try {
            return createDescriptors(aClass.getDeclaredFields());
        } catch (Throwable e) {
            return new CollectedClassMemberFieldDescriptors(null);
        }
    }
    
    /**
     * Returns a collection of field descriptors for the specified class.
     *
     * @param fields the fields for which to create field descriptors.
     * @return a new collection of field descriptors for the specified class.
     */
    public static FieldDescriptors createDescriptors(final Field... fields) {
        try {
            return new CollectedClassMemberFieldDescriptors(fields);
        } catch (Throwable e) {
            return new CollectedClassMemberFieldDescriptors(null);
        }
    }
}
