package reivosar.common.util.reflect;

import java.util.Arrays;

class ConstructorDescriptors extends ClassMemberDescriptors<ConstructorDescriptor> {
    
    ConstructorDescriptors(final Class<?> aClass) {
        super(Arrays.stream(aClass.getConstructors())
                .map(ConstructorDescriptor::new)
                .toList());
    }
}