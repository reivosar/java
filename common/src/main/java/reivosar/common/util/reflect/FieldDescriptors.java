package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Field;
import java.util.Arrays;

class FieldDescriptors extends ClassMemberDescriptors<FieldDescriptor> {
    
    FieldDescriptors(final Field[] fields) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(fields, new Field[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(FieldDescriptor::new)
                .toList());
    }
}