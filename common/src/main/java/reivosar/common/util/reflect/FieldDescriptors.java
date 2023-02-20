package reivosar.common.util.reflect;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Arrays;
import java.util.Objects;

class FieldDescriptors extends ClassMemberDescriptors<FieldDescriptor> {
    
    FieldDescriptors(final Class<?> aClass) {
        super(Arrays.stream(FieldUtils.getAllFields(aClass))
                .filter(Objects::nonNull)
                .map(FieldDescriptor::new)
                .toList());
    }
}