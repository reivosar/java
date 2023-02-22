package reivosar.common.util.reflect;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

class FieldAccessor {
    
    private final Field field;
    
    FieldAccessor(final Field field) {
        this.field = field;
        this.field.setAccessible(true);
    }
    
    Object readField(final Object target, final String fieldName) {
        try {
            return FieldUtils.readField(field, target);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    Object readStaticField() {
        try {
            return FieldUtils.readStaticField(field);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    void writeField(final Object target, final Object value) {
        try {
            FieldUtils.writeField(field, target, value);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    void writeStaticField(final Object value) {
        try {
            FieldUtils.writeStaticField(field, value);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
