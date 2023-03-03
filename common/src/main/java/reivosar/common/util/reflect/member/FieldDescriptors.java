package reivosar.common.util.reflect.member;

import java.lang.annotation.Annotation;

/**
 * A collection of field descriptors.
 * Provides methods for filtering field descriptors by name or annotation.
 */
public interface FieldDescriptors extends MetadataDescriptors<FieldDescriptor> {
    
    /**
     * Returns a new collection of field descriptors filtered by name.
     *
     * @param name the name of the field to filter by
     * @return a new instance of {@code FieldDescriptors}  filtered by name
     */
    FieldDescriptors filter(String name);
    
    /**
     * Returns a new collection of field descriptors filtered by annotation.
     *
     * @param annotationClass the class of the annotation to filter by
     * @return a new instance of {@code FieldDescriptors} filtered by annotation
     */
    FieldDescriptors filter(Class<? extends Annotation> annotationClass);
}