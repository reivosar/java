package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;

class MetadataAccessor {
    
    private final ClassAccessibleObject accessibleObject;
    
    MetadataAccessor(final ClassAccessibleObject accessibleObject) {
        this.accessibleObject = ObjectUtil.defaultIfNull(accessibleObject, new ClassAccessibleObject(null));
    }
    
    Collection<Annotation> getAnnotatedMetadata() {
        return accessibleObject.getAnnotations();
    }
    
    boolean hasAnnotatedMetadata(final Class<? extends Annotation> annotationClass) {
        return accessibleObject.hasAnnotation(annotationClass);
    }
}
