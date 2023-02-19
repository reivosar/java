package reivosar.common.util.reflect;

import java.lang.annotation.Annotation;
import java.util.Collection;

/**
 * Represents metadata information about a class, such as the annotations it has.
 */
public interface ClassMetadata {
    
    /**
     * Returns a collection of all the annotations present on the class.
     *
     * @return a collection of annotations
     */
    Collection<Annotation> getAnnotations();
    
    /**
     * Checks if the class has an annotation of the given type.
     *
     * @param annotation the type of the annotation to check for
     * @return true if the class has the annotation, false otherwise
     */
    boolean hasAnnotation(Annotation annotation);
}