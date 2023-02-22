package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.annotation.Annotation;
import java.util.Collection;

abstract class ClassMemberMetadataDescriptors<T extends ClassMemberMetadataDescriptor<?>> extends Model {
    
    private final ClassMemberMetadataAccessor<T> metadataAccessor;
    
    ClassMemberMetadataDescriptors(final Collection<T> tCollection) {
        this.metadataAccessor = new ClassMemberMetadataAccessor<>(tCollection);
    }
    
    int count() {
        return metadataAccessor.count();
    }
    
    Collection<String> names() {
        return metadataAccessor.names();
    }
    
    Collection<T> descriptors() {
        return metadataAccessor.descriptors();
    }
    
    Collection<T> filterByName(final String name) {
        return metadataAccessor.filterByName(name);
    }
    
    Collection<T> filterByAnnotation(final Class<? extends Annotation> annotationClass) {
        return metadataAccessor.filterByAnnotation(annotationClass);
    }
}
