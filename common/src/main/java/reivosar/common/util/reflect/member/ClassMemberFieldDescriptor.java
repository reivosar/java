package reivosar.common.util.reflect.member;

import reivosar.common.util.reflect.type.FieldTypeDescriptor;
import reivosar.common.util.reflect.type.FieldTypeFactory;

import java.lang.reflect.Field;

class ClassMemberFieldDescriptor extends AbstractAnnotatedMetadataDescriptor<Field> implements FieldDescriptor {
    
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
