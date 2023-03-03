package reivosar.common.util.reflect.member;

import java.lang.annotation.Annotation;

/**
 * A collection of {@link MethodDescriptor} objects for a class.
 */
public interface MethodDescriptors extends ExecutableClassMemberDescriptors<MethodDescriptor> {
    
    /**
     * Filters the methods based on the specified name and parameters.
     *
     * @param name the name of the Method to filter by
     * @param parameters the parameters of the methods to filter by
     * @return a new instance of {@code MethodDescriptors} containing only the matching methods
     */
    MethodDescriptors filter(String name, Object... parameters);
    
    /**
     * Filters the methods based on the specified annotation and parameters.
     *
     * @param annotationClass the annotation class to filter by
     * @param parameters the parameters of the methods to filter by
     * @return a new instance of {@code MethodDescriptors} containing only the matching methods
     */
    MethodDescriptors filter(Class<? extends Annotation> annotationClass, Object... parameters);
}