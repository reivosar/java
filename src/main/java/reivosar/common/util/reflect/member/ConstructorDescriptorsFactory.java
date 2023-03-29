package reivosar.common.util.reflect.member;

import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;
import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Constructor;

/**
 * This class provides a factory constructor to create a collection of constructor descriptors for a given class.
 */
public class ConstructorDescriptorsFactory {
    
    private static final Cache<String, Constructor<?>[]> CACHE = CacheFactory.getEternalLocalCache();
    
    /**
     * Returns a collection of constructor descriptors for the specified class.
     *
     * @param aClass the class for which to create constructor descriptors.
     * @return a new collection of constructor descriptors for the specified class.
     */
    public static ConstructorDescriptors createDescriptors(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        try {
            return createDescriptors(CACHE.get(aClass.getName()).orElse(createCache(aClass)));
        } catch (Throwable e) {
            return new CollectedClassMemberConstructorDescriptors(null);
        }
    }
    
    private static Constructor<?>[] createCache(final Class<?> aClass) {
        CACHE.put(aClass.getName(), aClass.getDeclaredConstructors());
        return CACHE.get(aClass.getName()).firstValue();
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
