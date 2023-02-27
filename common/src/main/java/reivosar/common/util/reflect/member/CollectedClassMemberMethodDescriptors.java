package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;

class CollectedClassMemberMethodDescriptors extends CollectedExecutableClassMemberDescriptors<MethodDescriptor, MethodDescriptors>
        implements MethodDescriptors {
    
    protected CollectedClassMemberMethodDescriptors(final Method[] methods) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(methods, new Method[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(MethodDescriptorFactory::create)
                .toList());
    }
    
    @Override
    public Collection<MethodDescriptor> getMethodDescriptors() {
        return getDescriptors();
    }
}
