package reivosar.common.lang.reflect.member;

import reivosar.common.lang.ObjectUtil;
import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;

import java.lang.reflect.Method;

/**
 * This class provides a factory method to append a collection of method descriptors for a given class.
 */
public class MethodDescriptorsFactory {
    
    private static final Cache<String, Method[]> CACHE = CacheFactory.getEternalLocalCache();
    
    /**
     * Returns a collection of method descriptors for the specified class.
     *
     * @param aClass the class for which to append method descriptors.
     * @return a new collection of method descriptors for the specified class.
     */
    public static MethodDescriptors createDescriptors(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        try {
            return createDescriptors(CACHE.getOrPut(aClass.getName(), aClass.getDeclaredMethods()).nullableFirstValue());
        } catch (Throwable e) {
            return new CollectedClassMemberMethodDescriptors(null);
        }
    }
    
    /**
     * Returns a collection of method descriptors for the specified methods.
     *
     * @param methods the methods for which to append method descriptors.
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
