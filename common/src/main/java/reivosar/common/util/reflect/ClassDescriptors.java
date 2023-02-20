package reivosar.common.util.reflect;

import com.google.common.reflect.ClassPath;
import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;
import reivosar.common.util.model.Model;

import java.io.IOException;

public class ClassDescriptors extends Model {
    
    private static final Cache<String, ClassDescriptor> CACHE;
    
    static {
        CACHE = CacheFactory.getEternalLocalCache();
        try {
            ClassPath.from(Thread.currentThread().getContextClassLoader())
                    .getAllClasses()
                    .forEach(classInfo -> CACHE.put(classInfo.getName(), new ClassDescriptor(classInfo.getClass())));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    public static ClassDescriptor findByClassPath(final String classPath) {
        return CACHE.get(classPath).nullableValue();
    }
}
