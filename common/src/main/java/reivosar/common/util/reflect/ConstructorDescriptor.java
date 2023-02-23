package reivosar.common.util.reflect;

import java.lang.reflect.Constructor;

class ConstructorDescriptor extends ExecutableClassMemberDescriptor {
    
    private final ConstructorExecutor constructorExecutor;
    
    ConstructorDescriptor(final Constructor<?> constructor) {
        super(constructor);
        this.constructorExecutor = new ConstructorExecutor(constructor);
    }
    
    <T> T newInstance(final Object... parameters) {
        return constructorExecutor.newInstance(parameters);
    }
}
