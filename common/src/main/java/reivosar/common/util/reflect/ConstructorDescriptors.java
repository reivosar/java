package reivosar.common.util.reflect;

import java.util.Arrays;
import java.util.Objects;

class ConstructorDescriptors extends ClassMemberDescriptors<ConstructorDescriptor> {
    
    ConstructorDescriptors(final Class<?> aClass) {
        super(Arrays.stream(aClass.getConstructors())
                .filter(Objects::nonNull)
                .map(ConstructorDescriptor::new)
                .toList());
    }
}