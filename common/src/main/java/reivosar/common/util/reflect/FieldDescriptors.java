package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

class FieldDescriptors extends ClassMemberDescriptors<FieldDescriptor> {
    
    FieldDescriptors(final Field[] fields) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(fields, new Field[0]))
                .filter(Objects::nonNull)
                .map(FieldDescriptor::new)
                .toList());
    }
}