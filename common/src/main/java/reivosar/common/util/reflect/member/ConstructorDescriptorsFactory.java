package reivosar.common.util.reflect.member;

/**
 * This class provides a factory constructor to create a collection of constructor descriptors for a given class.
 */
public class ConstructorDescriptorsFactory {
    
    /**
     * Returns a collection of Constructor descriptors for the specified class.
     *
     * @param aClass the class for which to create constructor descriptors.
     * @return a new collection of constructor descriptors for the specified class.
     */
    public static ConstructorDescriptors createDescriptors(final Class<?> aClass) {
        try {
            return new CollectedClassMemberConstructorDescriptors(aClass.getDeclaredConstructors());
        } catch (Throwable e) {
            return new CollectedClassMemberConstructorDescriptors(null);
        }
    }
}
