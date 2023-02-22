package reivosar.common.util.reflect;

import java.lang.reflect.Constructor;

class ConstructorDescriptor extends ExecutableClassMemberDescriptor<Constructor<?>> {
    
    private final ConstructorInvoker constructorInvoker;
    
    ConstructorDescriptor(final Constructor<?> constructor) {
        super(constructor);
        this.constructorInvoker = new ConstructorInvoker(constructor);
    }
    
    <T> T newInstance(final Object... parameters) {
        return constructorInvoker.newInstance(parameters);
    }
}
