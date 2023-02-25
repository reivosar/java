package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import reivosar.common.util.reflect.member.MethodDescriptor;
import reivosar.common.util.reflect.member.MethodDescriptorFactory;

import java.lang.reflect.Method;
import java.util.Arrays;

class MethodDescriptors extends ExecutableClassMemberDescriptors<MethodDescriptor> {
    
    MethodDescriptors(final Method[] methods) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(methods, new Method[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(MethodDescriptorFactory::create)
                .toList());
    }
}
