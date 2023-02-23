package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

class ClassMemberCollectionAccessor<T extends ClassMemberDescriptor> extends Model {
    
    private final Collection<T> tCollection;
    
    protected ClassMemberCollectionAccessor(final Collection<T> tCollection) {
        this.tCollection = tCollection;
    }
    
    Collection<T> filter(final String name) {
        return filter(t -> t.equalsByName(name));
    }
    
    protected List<T> filter(final Predicate<T> predicate) {
        return tCollection.stream()
                .filter(predicate)
                .toList();
    }
    
    int getMemberCount() {
        return tCollection.size();
    }
    
    Collection<T> getDescriptors() {
        return tCollection;
    }
    
    Collection<String> getMemberNames() {
        return tCollection.stream()
                .map(ClassMemberDescriptor::getName)
                .toList();
    }
}
