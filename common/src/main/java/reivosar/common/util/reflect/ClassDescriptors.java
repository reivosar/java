package reivosar.common.util.reflect;

import com.google.common.reflect.ClassPath;
import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;
import reivosar.common.util.model.Model;

import java.io.IOException;

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
public class ClassDescriptors extends Model {
    
    private static final Cache<String, ClassDescriptor> CACHE;
    
    static {
        try {
            CACHE = CacheFactory.getEternalLocalCache();
            for (final ClassPath.ClassInfo classInfo :
                    ClassPath.from(Thread.currentThread().getContextClassLoader()).getAllClasses()) {
                try {
                    CACHE.put(classInfo.getName(), new ClassDescriptor(classInfo.load()));
                } catch (Throwable e) {
                    // Do nothing
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    /**
     * Finds a cached class descriptor by its fully qualified class path.
     *
     * @param classPath the fully qualified class path of the class to find
     * @return the class descriptor if found, or null if not found
     */
    public static ClassDescriptor findByClassPath(final String classPath) {
        return CACHE.get(classPath).nullableValue();
    }
}
