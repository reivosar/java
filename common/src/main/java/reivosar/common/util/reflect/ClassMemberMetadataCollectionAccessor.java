package reivosar.common.util.reflect;

import java.lang.annotation.Annotation;
import java.util.Collection;

class ClassMemberMetadataCollectionAccessor<T extends ClassMemberMetadataDescriptor<?>> extends ClassMemberCollectionAccessor<T> {
    
    ClassMemberMetadataCollectionAccessor(final Collection<T> tCollection) {
        super(tCollection);
    }
    
    Collection<T> filter(final Class<? extends Annotation> annotationClass) {
        return filter(t -> t.hasAnnotatedMetadata(annotationClass));
    }
}
