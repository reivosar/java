package reivosar.common.util.reflect.member;

import java.lang.annotation.Annotation;

/**
 * A collection of {@link ConstructorDescriptor} objects for a class.
 */
public interface ConstructorDescriptors extends ExecutableClassMemberDescriptors<ConstructorDescriptor> {
    
    /**
     * Filters the constructors based on the specified name and parameters.
     *
     * @param name       the name of the constructor to filter by
     * @param parameters the parameters of the constructor to filter by
     * @return a new instance of {@code ConstructorDescriptors} containing only the matching constructors
     */
    ConstructorDescriptors filter(String name, Object... parameters);
    
    /**
     * Filters the constructors based on the specified annotation and parameters.
     *
     * @param annotationClass the annotation class to filter by
     * @param parameters      the parameters of the constructor to filter by
     * @return a new instance of {@code ConstructorDescriptors} containing only the matching constructors
     */
    ConstructorDescriptors filter(Class<? extends Annotation> annotationClass, Object... parameters);
}