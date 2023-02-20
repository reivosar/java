package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

class MethodDescriptors extends ClassMemberDescriptors<MethodDescriptor> {
    
    MethodDescriptors(final Method[] methods) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(methods, new Method[0]))
                .filter(Objects::nonNull)
                .map(MethodDescriptor::new)
                .toList());
    }
}
