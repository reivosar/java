package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Constructor;
import java.util.Arrays;

class CollectedClassMemberConstructorDescriptors
        extends CollectedExecutableClassMemberDescriptors<ConstructorDescriptor, ConstructorDescriptors>
        implements ConstructorDescriptors {
    
    CollectedClassMemberConstructorDescriptors(final Constructor<?>[] constructors) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(constructors, new Constructor[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(ConstructorDescriptorFactory::create)
                .toList());
    }
}

