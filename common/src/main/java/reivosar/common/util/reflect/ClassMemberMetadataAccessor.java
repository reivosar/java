package reivosar.common.util.reflect;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

class ClassMemberMetadataAccessor<T extends ClassMemberMetadataDescriptor<?>> {
    
    private final Collection<T> tCollection;
    
    ClassMemberMetadataAccessor(final Collection<T> tCollection) {
        this.tCollection = tCollection;
    }
    
    int count() {
        return tCollection.size();
    }
    
    Collection<String> names() {
        return tCollection.stream()
                .map(ClassMemberDescriptor::getName)
                .toList();
    }
    
    Collection<T> descriptors() {
        return Collections.unmodifiableCollection(tCollection);
    }
    
    Collection<T> filterByName(final String name) {
        return filter(t -> t.equalsByName(name));
    }
    
    Collection<T> filterByAnnotation(final Class<? extends Annotation> annotationClass) {
        return filter(t -> t.hasAnnotatedMetadata(annotationClass));
    }
    
    private List<T> filter(final Predicate<T> predicate) {
        return tCollection.stream()
                .filter(predicate)
                .toList();
    }
}
