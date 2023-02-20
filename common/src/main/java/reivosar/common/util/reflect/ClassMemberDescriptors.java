package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

abstract class ClassMemberDescriptors<T extends ClassMemberDescriptor> extends Model {
    
    private final Collection<T> tCollection;
    
    ClassMemberDescriptors(final Collection<T> tCollection) {
        this.tCollection = tCollection;
    }
    
    Collection<T> findByName(final String name) {
        return filter(t -> t.equalsByName(name));
    }
    
    Collection<T> findByAnnotation(final Annotation annotation) {
        return filter(t -> t.hasAnnotatedMetadata(annotation));
    }
    
    private List<T> filter(final Predicate<T> predicate) {
        return tCollection.stream().filter(predicate).toList();
    }

    Collection<T> descriptors() {
        return Collections.unmodifiableCollection(tCollection);
    }
}
