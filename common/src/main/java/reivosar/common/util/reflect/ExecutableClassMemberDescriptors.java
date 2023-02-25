package reivosar.common.util.reflect;

import reivosar.common.util.reflect.member.ClassMemberDescriptor;
import reivosar.common.util.reflect.member.MetadataDescriptor;
import reivosar.common.util.reflect.member.ExecutableClassMemberDescriptor;

import java.lang.annotation.Annotation;
import java.util.Collection;

class ExecutableClassMemberDescriptors<T extends ClassMemberDescriptor & MetadataDescriptor & ExecutableClassMemberDescriptor>
        extends ClassMemberMetadataDescriptors<T> {
    
    private final ExecutableClassMemberCollectionAccessor<T> collectionAccessor;
    
    protected ExecutableClassMemberDescriptors(final Collection<T> tCollection) {
        super(tCollection);
        this.collectionAccessor = new ExecutableClassMemberCollectionAccessor<>(tCollection);
    }
    
    Collection<T> filter(final String name, final Object... parameters) {
        return this.collectionAccessor.filter(name, parameters);
    }
    
    Collection<T> filter(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        return this.collectionAccessor.filter(annotationClass, parameters);
    }
}
