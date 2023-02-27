package reivosar.common.util.reflect.member;

import java.lang.annotation.Annotation;

/**
 * An interface that represents a descriptor for executable class members, such as methods and constructors, providing
 * access to the behavior and metadata of these members.
 * This interface extends {@code MetadataDescriptors<T>}.
 *
 * @param <T> the type of the object that implements this interface
 */
public interface ExecutableClassMemberDescriptors<T extends ExecutableClassMemberDescriptors<T>> extends MetadataDescriptors<T> {
    
    /**
     * Returns a filtered view of this executable class member descriptor, containing only those elements that match
     * the specified name and parameter values.
     *
     * @param name       the name to match against
     * @param parameters the parameter values to match against
     * @return a filtered view of this executable class member descriptor, containing only those elements that match
     * the specified name and parameter values
     */
    T filterWithParameters(final String name, final Object... parameters);
    
    /**
     * Returns a filtered view of this executable class member descriptor, containing only those elements that are
     * annotated with the specified annotation and match the specified parameter values.
     *
     * @param annotationClass the class of the annotation to filter by
     * @param parameters      the parameter values to match against
     * @return a filtered view of this executable class member descriptor, containing only those elements that
     * are annotated with the specified annotation and match the specified parameter values
     */
    T filterWithParameters(final Class<? extends Annotation> annotationClass, final Object... parameters);
}