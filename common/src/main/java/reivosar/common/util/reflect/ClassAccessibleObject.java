package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.util.Collection;
import java.util.List;

class ClassAccessibleObject extends Model {
    
    private final Collection<Annotation> annotations;
    
    ClassAccessibleObject(final AccessibleObject accessibleObject) {
        this.annotations = List.of(accessibleObject.getAnnotations());
    }
    
    Collection<Annotation> getAnnotations() {
        return annotations;
    }
    
    boolean hasAnnotation(Annotation annotation) {
        return annotations.contains(annotation);
    }
}
