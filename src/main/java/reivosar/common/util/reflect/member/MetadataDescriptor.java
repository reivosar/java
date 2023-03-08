package reivosar.common.util.reflect.member;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * An interface representing metadata information of a field or a method.
 */
public interface MetadataDescriptor {
    
    /**
     * Returns the collection of annotations of the field or method.
     *
     * @return a collection of annotations
     */
    Collection<Annotation> getAnnotations();
    
    /**
     * Checks whether the field or method has the specified annotation.
     *
     * @param annotationClass the annotation class to check for
     * @return {@code true} if the field or method has the annotation, {@code false} otherwise
     */
    boolean hasAnnotation(final Class<? extends Annotation> annotationClass);
}