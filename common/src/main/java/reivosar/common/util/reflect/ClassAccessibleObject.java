package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collection;
import java.util.List;

class ClassAccessibleObject extends Model {
    
    private final Collection<Annotation> annotations;
    
    ClassAccessibleObject(final AnnotatedElement annotatedElement) {
        this.annotations = (annotatedElement != null) ?
                List.of(annotatedElement.getAnnotations()) : List.of();
    }
    
    Collection<Annotation> getAnnotations() {
        return annotations;
    }
    
    boolean hasAnnotation(final Class<? extends Annotation> annotationClass) {
        return annotations.stream()
                .anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }
}
