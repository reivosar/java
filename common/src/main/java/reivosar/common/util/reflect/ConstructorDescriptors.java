package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Objects;

class ConstructorDescriptors extends ClassMemberDescriptors<ConstructorDescriptor> {
    
    ConstructorDescriptors(final Constructor<?>[] constructors) {
        super(Arrays.stream(ObjectUtil.defaultIfNull(constructors, new Constructor[]{}))
                .filter(ObjectUtil::isNotEmpty)
                .map(ConstructorDescriptor::new)
                .toList());
    }
}