package reivosar.common.util.reflect.member;

import reivosar.common.util.reflect.type.ConstructorTypeFactory;
import reivosar.common.util.reflect.type.ParameterTypesDescriptor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class ClassMemberConstructorDescriptor extends AbstractAnnotatedMetadataDescriptor<Constructor<?>> implements ConstructorDescriptor {
    
    private final ParameterTypesDescriptor parameterTypesDescriptor;
    private final ConstructorAccessor constructorAccessor;
    
    ClassMemberConstructorDescriptor(final Constructor<?> constructor) {
        super(constructor);
        this.parameterTypesDescriptor = ConstructorTypeFactory.createParameterTypes(constructor);
        this.constructorAccessor = new ClassMemberConstructorAccessor(constructor);
    }
    
    @Override
    public ParameterTypesDescriptor getParameterTypesDescriptor() {
        return this.parameterTypesDescriptor;
    }
    
    @Override
    public ConstructorAccessor getConstructorAccessor() {
        return this.constructorAccessor;
    }
}
