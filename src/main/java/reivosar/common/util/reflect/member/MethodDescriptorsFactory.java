package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Method;

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
        ObjectUtil.requireNonNull("aClass", aClass);
        return createDescriptors(aClass.getDeclaredMethods());
    }
    
    /**
     * Returns a collection of method descriptors for the specified methods.
     *
     * @param methods the methods for which to create method descriptors.
     * @return a new collection of method descriptors for the specified class.
     */
    public static MethodDescriptors createDescriptors(final Method... methods) {
        try {
            return new CollectedClassMemberMethodDescriptors(methods);
        } catch (Throwable e) {
            return new CollectedClassMemberMethodDescriptors(null);
        }
    }
}
