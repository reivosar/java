package reivosar.common.lang.reflect.member;

import reivosar.common.lang.reflect.type.FieldTypeDescriptor;
import reivosar.common.lang.reflect.type.FieldTypeFactory;

import java.lang.reflect.Field;

class ClassMemberFieldDescriptor extends MetadataDescriptorTemplate<Field> implements FieldDescriptor {
    
    private final FieldTypeDescriptor fieldTypeDescriptor;
    private final FieldAccessor fieldAccessor;
    
    ClassMemberFieldDescriptor(final Field field, final boolean forceAccess) {
        super(field);
        this.fieldTypeDescriptor = FieldTypeFactory.create(field);
        this.fieldAccessor = new ClassMemberFieldAccessor(field, forceAccess);
    }
    
    @Override
    public FieldTypeDescriptor getFieldTypeDescriptor() {
        return fieldTypeDescriptor;
    }
    
    @Override
    public FieldAccessor getFieldAccessor() {
        return fieldAccessor;
    }
}
