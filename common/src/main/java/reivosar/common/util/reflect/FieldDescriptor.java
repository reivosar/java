package reivosar.common.util.reflect;

import java.lang.reflect.Field;

class FieldDescriptor extends ClassMemberMetadataDescriptor<Field> {
    
    private final FieldAccessor fieldAccessor;
    
    FieldDescriptor(final Field field) {
        super(field);
        this.fieldAccessor = new FieldAccessor(field);
    }
    
    public Object readField(final Object target) {
        return fieldAccessor.readField(target);
    }
    
    public Object readStaticField() {
        return fieldAccessor.readStaticField();
    }
    
    public void writeField(final Object target, final Object value) {
        fieldAccessor.writeField(target, value);
    }
    
    public void writeStaticField(final Object value) {
        fieldAccessor.writeStaticField(value);
    }
}
