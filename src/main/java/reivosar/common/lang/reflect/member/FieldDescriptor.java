package reivosar.common.lang.reflect.member;

import reivosar.common.lang.reflect.type.FieldTypeDescriptor;

/**
 * Provides information about a field in a class, including its access scope, type, name, metadata, and accessor.
 */
public interface FieldDescriptor extends ClassMemberDescriptor, MetadataDescriptor {
    
    /**
     * Returns the descriptor for the field's type.
     *
     * @return the descriptor for the field's type
     */
    FieldTypeDescriptor getFieldTypeDescriptor();
    
    /**
     * Returns the accessor for the field.
     *
     * @return the accessor for the field
     */
    FieldAccessor getFieldAccessor();
}