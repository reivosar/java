package reivosar.common.util.reflect.member;

import java.lang.annotation.Annotation;

/**
 * An interface that represents a descriptor for metadata, providing access to metadata about a class member, such as a
 * field, method, or constructor.
 * This interface extends {@code ClassMemberDescriptors<T>}.
 *
 * @param <T> the type of the object that implements this interface
 */
public interface MetadataDescriptors<T extends MetadataDescriptors<T>> extends ClassMemberDescriptors<T> {
    
    /**
     * Returns a filtered view of this metadata descriptor, containing only those elements that are annotated with the
     * specified annotation.
     *
     * @param annotationClass the class of the annotation to filter by
     * @return a filtered view of this metadata descriptor, containing only those elements that are annotated with the
     * specified annotation
     */
    T filterByAnnotation(Class<? extends Annotation> annotationClass);
}