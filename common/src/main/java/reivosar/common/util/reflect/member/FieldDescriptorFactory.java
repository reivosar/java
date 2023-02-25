package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Field;

/**
 * A factory for creating {@link FieldDescriptor} objects from {@link Field} objects.
 */
public class FieldDescriptorFactory {
    
    /**
     * Creates a new {@link FieldDescriptor} object for the specified field.
     *
     * @param field the field for which to create the descriptor
     * @return a new {@link FieldDescriptor} object for the specified field
     * @throws NullPointerException if {@code field} is {@code null}
     */
    public static FieldDescriptor create(final Field field) {
        ObjectUtil.requireNonNull("field", field);
        return new ClassMemberFieldDescriptor(field);
    }
}
