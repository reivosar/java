package reivosar.common.util.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Member;

class ConstructorDescriptor extends ClassMemberDescriptor {
    
    private final Constructor<?> constructor;
    
    ConstructorDescriptor(final Constructor<?> constructor) {
        this.constructor = constructor;
    }
    
    @Override
    protected Member getMember() {
        return constructor;
    }
    
    @Override
    protected ClassAccessibleObject getClassAccessibleObject() {
        return new ClassAccessibleObject(this.constructor);
    }
}
