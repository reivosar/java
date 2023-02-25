package reivosar.common.util.reflect.type;

import reivosar.common.util.model.Model;

import java.lang.reflect.Field;

class FieldMemberTypeDescriptor extends Model implements FieldTypeDescriptor {
    
    private final Field field;
    
    FieldMemberTypeDescriptor(final Field field) {
        this.field = field;
    }
    
    @Override
    public String getName() {
        return field.getName();
    }
    
    @Override
    public Class<?> getRawType() {
        return field.getType();
    }
}
