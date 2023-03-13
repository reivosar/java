package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Constructor;

/**
 * This class provides a factory constructor to create a collection of constructor descriptors for a given class.
 */
public class ConstructorDescriptorsFactory {
    
    /**
     * Returns a collection of constructor descriptors for the specified class.
     *
     * @param aClass the class for which to create constructor descriptors.
     * @return a new collection of constructor descriptors for the specified class.
     */
    public static ConstructorDescriptors createDescriptors(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        try {
            return createDescriptors(aClass.getDeclaredConstructors());
        } catch (Throwable e) {
            return new CollectedClassMemberConstructorDescriptors(null);
        }
    }
    
    /**
     * Returns a collection of constructor descriptors for the specified constructors.
     *
     * @param constructors the constructors for which to create constructor descriptors.
     * @return a new collection of constructor descriptors for the specified class.
     */
    public static ConstructorDescriptors createDescriptors(final Constructor<?>... constructors) {
        try {
            return new CollectedClassMemberConstructorDescriptors(constructors);
        } catch (Throwable e) {
            return new CollectedClassMemberConstructorDescriptors(null);
        }
    }
}
