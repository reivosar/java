package reivosar.common.util.reflect.member;

import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;
import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * This class provides a factory field to create a collection of field descriptors for a given class.
 */
public class FieldDescriptorsFactory {
    
    private static final Cache<String, Field[]> CACHE = CacheFactory.getEternalLocalCache();
    
    /**
     * Returns a collection of field descriptors for the specified class.
     *
     * @param aClass the class for which to create field descriptors.
     * @return a new collection of field descriptors for the specified class.
     */
    public static FieldDescriptors createDescriptors(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        try {
            return createDescriptors(CACHE.get(aClass.getName()).orElse(createCache(aClass)));
        } catch (Throwable e) {
            return new CollectedClassMemberFieldDescriptors(null);
        }
    }
    
    private static Field[] createCache(final Class<?> aClass) {
        CACHE.put(aClass.getName(), aClass.getDeclaredFields());
        return CACHE.get(aClass.getName()).firstValue();
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
