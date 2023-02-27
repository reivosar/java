package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.annotation.Annotation;
import java.util.Collection;

@SuppressWarnings("unchecked")
class CollectedMetadataDescriptors<T extends ClassMemberDescriptor & MetadataDescriptor,
                                   S extends MetadataDescriptors<T, S>>
        extends CollectedClassMemberDescriptors<T, S> implements MetadataDescriptors<T, S> {
    
    protected CollectedMetadataDescriptors(final Collection<T> descriptors) {
        super(descriptors);
    }
    
    @Override
    public S filterByAnnotation(final Class<? extends Annotation> annotationClass) {
        ObjectUtil.requireNonNull("annotationClass", annotationClass);
        return (S) new CollectedMetadataDescriptors<T, S>(filter(getDescriptors(), t -> t.hasAnnotation(annotationClass)));
    }
}
