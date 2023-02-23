package reivosar.common.util.reflect;

import reivosar.common.util.lang.FieldUtil;
import reivosar.common.util.model.Model;

import java.lang.reflect.Field;

class FieldAccessor extends Model {
    
    private final Field field;
    
    FieldAccessor(final Field field) {
        this.field = field;
        this.field.setAccessible(true);
    }
    
    Object readField(final Object target) {
        return FieldUtil.readField(field, target);
    }
    
    Object readStaticField() {
        return FieldUtil.readStaticField(field);
    }
    
    void writeField(final Object target, final Object value) {
        FieldUtil.writeField(field, target, value);
    }
    
    void writeStaticField(final Object value) {
        FieldUtil.writeStaticField(field, value);
    }
}
