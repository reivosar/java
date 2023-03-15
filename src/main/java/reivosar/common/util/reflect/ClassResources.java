package reivosar.common.util.reflect;

import com.google.common.reflect.ClassPath;
import org.w3c.dom.stylesheets.LinkStyle;
import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;
import reivosar.common.util.cache.CacheValues;
import reivosar.common.util.lang.ClassUtil;
import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.reflect.member.MethodDescriptor;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    
    private static final Cache<String, ClassDescriptor> CACHE;
    
    static {
        try {
            CACHE = CacheFactory.getEternalLocalCache();
            for (final ClassPath.ClassInfo classInfo :
                    ClassPath.from(Thread.currentThread().getContextClassLoader()).getAllClasses()) {
                try {
                    CACHE.put(classInfo.getName(), ClassDescriptorFactory.create(classInfo.load()));
                } catch (Throwable e) {
                    // Do nothing
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
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
        return CACHE.get(classPath).nullableValue();
    }
    
    /**
     * Finds all class descriptors that have at least one method annotated with the specified annotation class
     * and having the specified parameters.
     *
     * @param annotationClass the annotation class to search for
     * @param parameters      the parameters to match against
     * @return a {@link ClassDescriptors} object containing all matching class descriptors
     */
    public static ClassDescriptors findByMethodAnnotation(
            final Class<? extends Annotation> annotationClass, final Object... parameters) {
        return findByMethodAnnotation(annotationClass, ClassUtil.toClass(parameters));
    }
    
    /**
     * Finds all class descriptors that have at least one method annotated with the specified annotation class
     * and having the specified parameters.
     *
     * @param annotationClass the annotation class to search for
     * @param parameters      the parameters to match against
     * @return a {@link ClassDescriptors} object containing all matching class descriptors
     */
    public static ClassDescriptors findByMethodAnnotation(
            final Class<? extends Annotation> annotationClass, final Class<?>... parameters) {
        final List<ClassDescriptor> results = CACHE.getAllValues().values()
                .stream()
                .filter(descriptor -> !descriptor.getMethodDescriptors()
                        .filter(annotationClass, parameters)
                        .getDescriptors().isEmpty())
                .collect(Collectors.toList());
        return new ClassDescriptors(results);
    }
    
    /**
     * Finds all class descriptors whose methods have the same parameter types as the given objects.
     *
     * @param parameters objects to use for finding matching parameter types
     * @return a {@link ClassDescriptors} object containing all matching class descriptors
     */
    public static ClassDescriptors findByMethodParameters(final Object... parameters) {
        return findByMethodParameters(ClassUtil.toClass(parameters));
    }
    
    /**
     * Finds all class descriptors whose methods have the same parameter types as the given classes.
     *
     * @param parameters classes to use for finding matching parameter types
     * @return a {@link ClassDescriptors} object containing all matching class descriptors
     */
    public static ClassDescriptors findByMethodParameters(final Class<?>... parameters) {
        final List<ClassDescriptor> results = CACHE.getAllValues().values()
                .stream()
                .filter(descriptor -> descriptor.getMethodDescriptors()
                        .getDescriptors()
                        .stream()
                        .anyMatch(md -> md.getParameterTypesDescriptor().isEqualParameterType(parameters)))
                .collect(Collectors.toList());
        return new ClassDescriptors(results);
    }
}
