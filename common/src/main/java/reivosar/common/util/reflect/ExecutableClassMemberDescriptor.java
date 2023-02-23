package reivosar.common.util.reflect;

import java.lang.reflect.Executable;
import java.util.Collection;

abstract class ExecutableClassMemberDescriptor extends ClassMemberMetadataDescriptor<Executable> {
    
    private final ParameterTypesDescriptor parameterTypesDescriptor;
    
    protected ExecutableClassMemberDescriptor(final Executable executableMember) {
        super(executableMember);
        this.parameterTypesDescriptor = new ParameterTypesDescriptor(executableMember.getParameterTypes());
    }
    
    public boolean isMatchParameters(final Object... parameters) {
        final ParameterTypesDescriptor parameterTypes = new ParameterTypesDescriptor(parameters);
        return parameterTypes.isEqualParameterType(parameterTypesDescriptor);
    }
    
    public int getParameterCount() {
        return parameterTypesDescriptor.getParameterCount();
    }
    
    public Collection<Class<?>> getParameterTypes() {
        return parameterTypesDescriptor.getParameterTypes();
    }
}
