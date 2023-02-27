package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.util.Collection;
import java.util.List;

abstract class AbstractMetadataDescriptor<T extends Member & AnnotatedElement> extends AbstractClassMemberDescriptor<T>
        implements MetadataDescriptor {
    
    private final Collection<Annotation> annotations;
    
    protected AbstractMetadataDescriptor(final T object) {
        super(object);
        this.annotations = (object != null) ? List.of(object.getAnnotations()) : List.of();
    }
    
    @Override
    public Collection<Annotation> getAnnotations() {
        return annotations;
    }
    
    @Override
    public boolean hasAnnotation(final Class<? extends Annotation> annotationClass) {
        ObjectUtil.requireNonNull("annotationClass", annotationClass);
        return annotations.stream()
                .anyMatch(annotation -> annotation.annotationType().equals(annotationClass));
    }
}
