package reivosar.common.util.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

class FieldDescriptor extends ClassMemberDescriptor {
    
    private final Field field;
    
    FieldDescriptor(final Field field) {
        this.field = field;
    }
    
    @Override
    protected Member getMember() {
        return field;
    }
    
    @Override
    protected ClassAccessibleObject getClassAccessibleObject() {
        return new ClassAccessibleObject(this.field);
    }
}
