package reivosar.common.lang.reflect.type;

import java.lang.reflect.Field;

class FieldMemberTypeDescriptor extends AbstractTypeDescriptor implements FieldTypeDescriptor {
    
    FieldMemberTypeDescriptor(final Field field) {
        super(field.getName(), field.getType());
    }
}
