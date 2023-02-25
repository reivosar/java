package reivosar.common.util.reflect;

import reivosar.common.util.reflect.member.ClassMemberDescriptor;
import reivosar.common.util.reflect.member.MetadataDescriptor;

import java.lang.annotation.Annotation;
import java.util.Collection;

class ClassMemberMetadataDescriptors<T extends ClassMemberDescriptor & MetadataDescriptor> extends ClassMemberDescriptors<T> {
    
    private final ClassMemberMetadataCollectionAccessor<T> metadataAccessor;
    
    protected ClassMemberMetadataDescriptors(final Collection<T> tCollection) {
        super(tCollection);
        this.metadataAccessor = new ClassMemberMetadataCollectionAccessor<>(tCollection);
    }
    
    Collection<T> filter(final Class<? extends Annotation> annotationClass) {
        return metadataAccessor.filter(annotationClass);
    }
}
