package reivosar.common.util.reflect.member;

import java.util.Collection;

/**
 * An interface that represents a descriptor for fields, providing access to the behavior and metadata of the fields.
 * This interface extends {@code MetadataDescriptors<FieldDescriptors>}.
 */
public interface FieldDescriptors extends MetadataDescriptors<FieldDescriptors> {
    
    /**
     * Returns a collection of field descriptors that are included in the object represented by this interface.
     *
     * @return a collection of field descriptors that are included in the object represented by this interface
     */
    Collection<FieldDescriptor> getFieldDescriptors();
}