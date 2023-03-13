package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.util.Collection;
import java.util.Collections;

public class ClassDescriptors extends Model {

    private final Collection<ClassDescriptor> collection;
    
    ClassDescriptors(final Collection<ClassDescriptor> collection) {
        this.collection = collection;
    }
    
    public Collection<ClassDescriptor> getClassDescriptors() {
        return Collections.unmodifiableCollection(collection);
    }
    
    public boolean hasDescriptor() {
        return !collection.isEmpty();
    }
}
