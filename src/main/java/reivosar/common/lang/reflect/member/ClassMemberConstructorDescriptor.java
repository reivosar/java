package reivosar.common.lang.reflect.member;

import reivosar.common.lang.reflect.type.ConstructorTypeFactory;
import reivosar.common.lang.reflect.type.ParameterTypesDescriptor;

import java.lang.reflect.Constructor;

class ClassMemberConstructorDescriptor extends MetadataDescriptorTemplate<Constructor<?>> implements ConstructorDescriptor {
    
    private final ParameterTypesDescriptor parameterTypesDescriptor;
    private final ConstructorAccessor constructorAccessor;
    
    ClassMemberConstructorDescriptor(final Constructor<?> constructor, final boolean forceAccess) {
        super(constructor);
        this.parameterTypesDescriptor = ConstructorTypeFactory.createParameterTypes(constructor);
        this.constructorAccessor = new ClassMemberConstructorAccessor(constructor, forceAccess);
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
