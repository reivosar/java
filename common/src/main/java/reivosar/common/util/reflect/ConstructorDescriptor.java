package reivosar.common.util.reflect;

import java.lang.reflect.Constructor;

class ConstructorDescriptor extends ClassMemberDescriptor {
    
    ConstructorDescriptor(final Constructor<?> constructor) {
        super(constructor.getName(), constructor.getModifiers(), constructor);
    }
}
