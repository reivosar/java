package reivosar.common.lang.reflect.member;

import reivosar.common.lang.reflect.type.MethodTypeFactory;
import reivosar.common.lang.reflect.type.ParameterTypesDescriptor;
import reivosar.common.lang.reflect.type.ReturnTypeDescriptor;

import java.lang.reflect.Method;

class ClassMemberMethodDescriptor extends MetadataDescriptorTemplate<Method> implements MethodDescriptor {
    
    private final ReturnTypeDescriptor returnTypeDescriptor;
    private final ParameterTypesDescriptor parameterTypesDescriptor;
    private final MethodAccessor methodAccessor;
    
    ClassMemberMethodDescriptor(final Method method, final boolean forceAccess) {
        super(method);
        this.returnTypeDescriptor = MethodTypeFactory.createReturnType(method);
        this.parameterTypesDescriptor = MethodTypeFactory.createParameterTypes(method);
        this.methodAccessor = new ClassMemberMethodAccessor(method, forceAccess);
    }
    
    @Override
    public ReturnTypeDescriptor getReturnTypeDescriptor() {
        return returnTypeDescriptor;
    }
    
    @Override
    public ParameterTypesDescriptor getParameterTypesDescriptor() {
        return parameterTypesDescriptor;
    }
    
    @Override
    public MethodAccessor getMethodAccessor() {
        return methodAccessor;
    }
}

