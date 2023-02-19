package reivosar.common.util.reflect;

import java.util.Arrays;

class MethodDescriptors extends ClassMemberDescriptors<MethodDescriptor> {
    
    MethodDescriptors(final Class<?> aClass) {
        super(Arrays.stream(aClass.getMethods())
                .map(MethodDescriptor::new)
                .toList());
    }
}
