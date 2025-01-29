package reivosar.common.lang.reflect.member;

import reivosar.common.lang.ObjectUtil;
import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;

import java.lang.reflect.Constructor;

/**
 * This class provides a factory constructor to append a collection of constructor descriptors for a given class.
 */
public class ConstructorDescriptorsFactory {
    
    private static final Cache<String, Constructor<?>[]> CACHE = CacheFactory.getEternalLocalCache();
    
    /**
     * Returns a collection of constructor descriptors for the specified class.
     *
     * @param aClass the class for which to append constructor descriptors.
     * @return a new collection of constructor descriptors for the specified class.
     */
    public static ConstructorDescriptors createDescriptors(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        try {
            return createDescriptors(CACHE.getOrPut(aClass.getName(), aClass.getDeclaredConstructors()).nullableFirstValue());
        } catch (Throwable e) {
            return new CollectedClassMemberConstructorDescriptors(null);
        }
    }
    
    /**
     * Returns a collection of constructor descriptors for the specified constructors.
     *
     * @param constructors the constructors for which to append constructor descriptors.
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
