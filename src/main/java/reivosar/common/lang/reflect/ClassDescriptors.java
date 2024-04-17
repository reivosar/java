package reivosar.common.lang.reflect;

import reivosar.common.data.model.Model;

import java.util.Collection;
import java.util.Collections;

/**
 * A container for a collection of {@link ClassDescriptor}s.
 */
public class ClassDescriptors extends Model {
    
    private final Collection<ClassDescriptor> collection;
    
    ClassDescriptors(final Collection<ClassDescriptor> collection) {
        this.collection = collection;
    }
    
    /**
     * Returns an unmodifiable view of the collection of {@link ClassDescriptor}s in this {@code ClassDescriptors} object.
     *
     * @return an unmodifiable view of the collection of {@link ClassDescriptor}s in this {@code ClassDescriptors} object
     */
    public Collection<ClassDescriptor> getClassDescriptors() {
        return Collections.unmodifiableCollection(collection);
    }
    
    /**
     * Returns {@code true} if this {@code ClassDescriptors} object contains at least one {@link ClassDescriptor}, {@code false} otherwise.
     *
     * @return {@code true} if this {@code ClassDescriptors} object contains at least one {@link ClassDescriptor}, {@code false} otherwise
     */
    public boolean hasDescriptor() {
        return !collection.isEmpty();
    }
}