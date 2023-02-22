package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Method;
import java.util.Arrays;

class MethodMetadataDescriptors extends ClassMemberMetadataDescriptors<MethodDescriptor> {
    
    MethodMetadataDescriptors(final Method[] methods) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(methods, new Method[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(MethodDescriptor::new)
                .toList());
    }
}
