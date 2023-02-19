package reivosar.common.util.reflect;

import java.lang.annotation.Annotation;
import java.util.Collection;

class MetadataAccessor {
    
    private final ClassAccessibleObject accessibleObject;
    
    MetadataAccessor(final ClassAccessibleObject accessibleObject) {
        this.accessibleObject = accessibleObject;
    }
    
    Collection<Annotation> getAnnotatedMetadata() {
        return accessibleObject.getAnnotations();
    }
    
    boolean hasAnnotatedMetadata(final Annotation annotation) {
        return getAnnotatedMetadata().contains(annotation);
    }
}
