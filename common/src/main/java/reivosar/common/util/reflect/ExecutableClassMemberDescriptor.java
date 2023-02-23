package reivosar.common.util.reflect;

import java.lang.reflect.Executable;
import java.util.Collection;

abstract class ExecutableClassMemberDescriptor extends ClassMemberMetadataDescriptor<Executable> {
    
    private final ParameterTypesDescriptor parameterTypesDescriptor;
    
    protected ExecutableClassMemberDescriptor(final Executable executableMember) {
        super(executableMember);
        this.parameterTypesDescriptor = new ParameterTypesDescriptor(executableMember.getParameterTypes());
    }
    
    boolean isMatchParameters(final Object... parameters) {
        final ParameterTypesDescriptor parameterTypes = new ParameterTypesDescriptor(parameters);
        return parameterTypes.isEqualParameterType(parameterTypesDescriptor);
    }
    
    int getParameterCount() {
        return parameterTypesDescriptor.getParameterCount();
    }
    
    Collection<Class<?>> getParameterTypes() {
        return parameterTypesDescriptor.getParameterTypes();
    }
}
