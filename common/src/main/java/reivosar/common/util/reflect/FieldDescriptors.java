package reivosar.common.util.reflect;

import java.util.Arrays;

class FieldDescriptors extends ClassMemberDescriptors<FieldDescriptor> {
    
    FieldDescriptors(final Class<?> aClass) {
        super(Arrays.stream(aClass.getFields())
                .map(FieldDescriptor::new)
                .toList());
    }
}