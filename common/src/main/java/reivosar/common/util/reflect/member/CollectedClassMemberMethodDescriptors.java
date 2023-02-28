package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Method;
import java.util.Arrays;

class CollectedClassMemberMethodDescriptors
        extends CollectedExecutableClassMemberDescriptors<MethodDescriptor, MethodDescriptors>
        implements MethodDescriptors {
    
    CollectedClassMemberMethodDescriptors(final Method[] methods) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(methods, new Method[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(MethodDescriptorFactory::create)
                .toList());
    }
}
