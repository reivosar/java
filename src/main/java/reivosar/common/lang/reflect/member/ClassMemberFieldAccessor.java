package reivosar.common.lang.reflect.member;

import reivosar.common.lang.FieldUtil;
import reivosar.common.lang.ObjectUtil;
import reivosar.common.data.model.Model;

import java.lang.reflect.Field;

class ClassMemberFieldAccessor extends Model implements FieldAccessor {
    
    private final Field field;
    
    ClassMemberFieldAccessor(final Field field, final boolean forceAccess) {
        this.field = field;
        this.field.setAccessible(forceAccess);
    }
    
    @Override
    public Object readField(final Object target) {
        ObjectUtil.requireNonNull("target", target);
        return FieldUtil.readField(field, target);
    }
    
    @Override
    public Object readStaticField() {
        return FieldUtil.readStaticField(field);
    }
    
    @Override
    public void writeField(final Object target, final Object value) {
        ObjectUtil.requireNonNull("target", target);
        ObjectUtil.requireNonNull("value", value);
        FieldUtil.writeField(field, target, value);
    }
    
    @Override
    public void writeStaticField(final Object value) {
        ObjectUtil.requireNonNull("value", value);
        FieldUtil.writeStaticField(field, value);
    }
}
