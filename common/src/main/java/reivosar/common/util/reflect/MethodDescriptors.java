package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Method;
import java.util.Arrays;

class MethodDescriptors extends ClassMemberDescriptors<MethodDescriptor> {
    
    MethodDescriptors(final Method[] methods) {
        super(Arrays.stream(ObjectUtil.getIfNull(methods, new Method[0]))
                .map(MethodDescriptor::new)
                .toList());
    }
}
