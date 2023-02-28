package reivosar.common.util.reflect.member;

/**
 * This class provides a factory method to create a collection of method descriptors for a given class.
 */
public class MethodDescriptorsFactory {
    
    /**
     * Returns a collection of method descriptors for the specified class.
     *
     * @param aClass the class for which to create method descriptors.
     * @return a new collection of method descriptors for the specified class.
     */
    public static MethodDescriptors createDescriptors(final Class<?> aClass) {
        try {
            return new CollectedClassMemberMethodDescriptors(aClass.getDeclaredMethods());
        } catch (Throwable e) {
            return new CollectedClassMemberMethodDescriptors(null);
        }
    }
}
