package reivosar.common.util.reflect;

import reivosar.common.util.reflect.member.ClassMemberDescriptor;
import reivosar.common.util.reflect.member.MetadataDescriptor;

import java.lang.annotation.Annotation;
import java.util.Collection;

class ClassMemberMetadataCollectionAccessor<T extends ClassMemberDescriptor & MetadataDescriptor> extends ClassMemberCollectionAccessor<T> {
    
    ClassMemberMetadataCollectionAccessor(final Collection<T> tCollection) {
        super(tCollection);
    }
    
    Collection<T> filter(final Class<? extends Annotation> annotationClass) {
        return filter(getDescriptors(), t -> t.hasAnnotation(annotationClass));
    }
}
