package reivosar.common.util.reflect;

import java.lang.reflect.Field;

class FieldDescriptor extends ClassMemberMetadataDescriptor<Field> {
    
    private final FieldAccessor fieldAccessor;
    
    FieldDescriptor(final Field field) {
        super(field);
        this.fieldAccessor = new FieldAccessor(field);
    }
    
    Object readField(final Object target) {
        return fieldAccessor.readField(target);
    }
    
    Object readStaticField() {
        return fieldAccessor.readStaticField();
    }
    
    void writeField(final Object target, final Object value) {
        fieldAccessor.writeField(target, value);
    }
    
    void writeStaticField(final Object value) {
        fieldAccessor.writeStaticField(value);
    }
}
