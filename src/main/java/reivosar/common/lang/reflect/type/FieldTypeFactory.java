package reivosar.common.lang.reflect.type;

import java.lang.reflect.Field;

/**
 * The FieldTypeDescriptorFactory class is a factory for creating instances of the FieldTypeDescriptor interface.
 * <p>
 * It provides a single static method for creating new instances of FieldTypeDescriptor based on the given Field parameter.
 */
public class FieldTypeFactory {
    
    /**
     * Creates a new instance of FieldTypeDescriptor for the given Field.
     *
     * @param field The Field object to create a FieldTypeDescriptor for.
     * @return A new instance of FieldTypeDescriptor that describes the type of the given field.
     */
    public static FieldTypeDescriptor create(final Field field) {
        return new FieldMemberTypeDescriptor(field);
    }
}