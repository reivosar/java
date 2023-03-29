package reivosar.common.util.reflect.member;

import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;
import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.Instant;

/**
 * This class provides a factory method to create a collection of method descriptors for a given class.
 */
public class MethodDescriptorsFactory {
    
    private static final Cache<String, Method[]> CACHE = CacheFactory.getEternalLocalCache();
    
    /**
     * Returns a collection of method descriptors for the specified class.
     *
     * @param aClass the class for which to create method descriptors.
     * @return a new collection of method descriptors for the specified class.
     */
    public static MethodDescriptors createDescriptors(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        try {
            return createDescriptors(CACHE.get(aClass.getName()).orElse(createCache(aClass)));
        } catch (Throwable e) {
            return new CollectedClassMemberMethodDescriptors(null);
        }
    }
    
    private static Method[] createCache(final Class<?> aClass) {
        CACHE.put(aClass.getName(), aClass.getDeclaredMethods());
        return CACHE.get(aClass.getName()).firstValue();
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
