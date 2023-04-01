package reivosar.common.util.reflect;

import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;
import reivosar.common.util.lang.ObjectUtil;

import java.util.Collection;
import java.util.Collections;

/**
 * Provides a cache of {@link ClassDescriptor} objects for classes that can be found in the application's classpath.
 * The cache is populated at startup with all the classes found in the classpath.
 * <p>
 * This class is a singleton and provides a static method to retrieve a {@link ClassDescriptor} for a given classpath.
 * <p>
 * The cache implementation is backed by a local cache with eternal expiration, meaning that the cache entries will
 * never expire or be evicted due to time or size constraints. As a consequence, memory usage might grow over time
 * depending on the number of unique classes accessed through this cache.
 */
public class ClassResources {
    
    private static final Cache<String, ClassDescriptor> CLASS_DESCRIPTOR_CACHE;
    
    static {
        CLASS_DESCRIPTOR_CACHE = CacheFactory.getEternalLocalCache();
        ClassDescriptorCache.getCache().forEach(CLASS_DESCRIPTOR_CACHE::put);
    }
    
    /**
     * Finds the {@code ClassDescriptor} for the given {@code Class}.
     *
     * @param clazz the {@code Class} to look up
     * @return the {@code ClassDescriptor} for the given {@code Class}, or {@code null} if it is not found
     */
    public static ClassDescriptor of(final Class<?> clazz) {
        ObjectUtil.requireNonNull("clazz", clazz);
        return of(clazz.getName());
    }
    
    /**
     * Finds a cached class descriptor by its fully qualified class path.
     *
     * @param classPath the fully qualified class path of the class to find
     * @return the class descriptor if found, or null if not found
     */
    public static ClassDescriptor of(final String classPath) {
        ObjectUtil.requireNonNull("classPath", classPath);
        return CLASS_DESCRIPTOR_CACHE.nullableFirstValue(classPath);
    }
    
    /**
     * Returns an unmodifiable collection of all class descriptors stored in the class descriptor cache.
     *
     * @return an unmodifiable collection of all class descriptors
     */
    public static Collection<ClassDescriptor> getAllClassDescriptors() {
        return Collections.unmodifiableCollection(CLASS_DESCRIPTOR_CACHE.getAllValues().values());
    }
}
