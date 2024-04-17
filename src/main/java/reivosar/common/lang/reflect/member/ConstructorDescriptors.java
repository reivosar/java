package reivosar.common.lang.reflect.member;

import java.lang.annotation.Annotation;

/**
 * A collection of {@link ConstructorDescriptor} objects for a class.
 */
public interface ConstructorDescriptors extends ExecutableClassMemberDescriptors<ConstructorDescriptor> {
    
    /**
     * Filters the constructors based on the specified name and parameters.
     *
     * @param parameterTypes the parameterTypes of the constructor to filter by
     * @return a new instance of {@code ConstructorDescriptors} containing only the matching constructors
     */
    ConstructorDescriptors filter(Class<?>... parameterTypes);
    
    /**
     * Filters the constructors based on the specified name and parameters.
     *
     * @param parameters the parameters of the constructor to filter by
     * @return a new instance of {@code ConstructorDescriptors} containing only the matching constructors
     */
    ConstructorDescriptors filter(Object... parameters);
    
    /**
     * Filters the constructors based on the specified annotation and parameters.
     *
     * @param annotationClass the annotation class to filter by
     * @param parameterTypes  the parameterTypes of the constructor to filter by
     * @return a new instance of {@code ConstructorDescriptors} containing only the matching constructors
     */
    ConstructorDescriptors filter(Class<? extends Annotation> annotationClass, Class<?>... parameterTypes);
    
    /**
     * Filters the constructors based on the specified annotation and parameters.
     *
     * @param annotationClass the annotation class to filter by
     * @param parameters      the parameters of the constructor to filter by
     * @return a new instance of {@code ConstructorDescriptors} containing only the matching constructors
     */
    ConstructorDescriptors filter(Class<? extends Annotation> annotationClass, Object... parameters);
}