package reivosar.common.util.reflect.member;

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
        try {
            return new CollectedClassMemberFieldDescriptors(aClass.getDeclaredFields());
        } catch (Throwable e) {
            return new CollectedClassMemberFieldDescriptors(null);
        }
    }
}
