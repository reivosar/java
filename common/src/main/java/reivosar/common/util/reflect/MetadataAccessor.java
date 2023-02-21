package reivosar.common.util.reflect;

import org.apache.commons.lang3.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;

class MetadataAccessor {
    
    private final ClassAccessibleObject accessibleObject;
    
    MetadataAccessor(final ClassAccessibleObject accessibleObject) {
        this.accessibleObject = accessibleObject;
    }
    
    Collection<Annotation> getAnnotatedMetadata() {
        return (accessibleObject != null) ?
                accessibleObject.getAnnotations() :
                Collections.emptyList();
    }
    
    boolean hasAnnotatedMetadata(final Class<? extends Annotation> annotationClass) {
        return getAnnotatedMetadata().stream().anyMatch(cachedAnnotation ->
                cachedAnnotation.annotationType().equals(annotationClass)
        );
    }
}
