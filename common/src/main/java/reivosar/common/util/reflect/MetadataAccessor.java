package reivosar.common.util.reflect;

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
    
    boolean hasAnnotatedMetadata(final Annotation annotation) {
        return getAnnotatedMetadata().contains(annotation);
    }
}
