package reivosar.common.util.reflect.member;

import java.lang.annotation.Annotation;

/**
 * This interface represents a collection of metadata descriptors that are also class member descriptors, which
 * include methods and fields.
 *
 * @param <T> the type of the metadata descriptor that is also a class member descriptor.
 * @param <S> the type of the metadata descriptors that are also class member descriptors.
 */
public interface MetadataDescriptors<T extends ClassMemberDescriptor & MetadataDescriptor,
                                     S extends MetadataDescriptors<T, S>>
        extends ClassMemberDescriptors<T, S> {
    
    /**
     * Filters the metadata descriptors by annotation.
     *
     * @param annotationClass the annotation to filter by.
     * @return a new collection of metadata descriptors filtered by the annotation.
     */
    S filterByAnnotation(Class<? extends Annotation> annotationClass);
}