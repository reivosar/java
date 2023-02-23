package reivosar.common.util.reflect;

import java.lang.annotation.Annotation;
import java.util.Collection;

class ExecutableClassMemberDescriptors<T extends ExecutableClassMemberDescriptor> extends ClassMemberMetadataDescriptors<T> {
    
    private final ExecutableClassMemberCollectionAccessor<T> collectionAccessor;
    
    protected ExecutableClassMemberDescriptors(final Collection<T> tCollection) {
        super(tCollection);
        this.collectionAccessor = new ExecutableClassMemberCollectionAccessor<>(tCollection);
    }
    
    Collection<T> filter(final String name, final Object... parameters) {
        final ExecutableClassMemberDescriptors<T> descriptors = new ExecutableClassMemberDescriptors<>(filter(name));
        return descriptors.collectionAccessor.filter(parameters);
    }
    
    Collection<T> filter(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        final ExecutableClassMemberDescriptors<T> descriptors = new ExecutableClassMemberDescriptors<>(filter(annotationClass));
        return descriptors.collectionAccessor.filter(parameters);
    }
}
