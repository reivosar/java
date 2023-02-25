package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import reivosar.common.util.reflect.member.ConstructorDescriptor;
import reivosar.common.util.reflect.member.ConstructorDescriptorFactory;

import java.lang.reflect.Constructor;
import java.util.Arrays;

class ConstructorDescriptors extends ExecutableClassMemberDescriptors<ConstructorDescriptor> {
    
    ConstructorDescriptors(final Constructor<?>[] constructors) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(constructors, new Constructor[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(ConstructorDescriptorFactory::create)
                .toList());
    }
}