package reivosar.common.util.reflect;

import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Executable;
import java.lang.reflect.Member;
import java.util.Collection;

abstract class ExecutableClassMemberDescriptor<T extends Executable & Member & AnnotatedElement>
        extends ClassMemberMetadataDescriptor<T> {
    
    private final ParameterTypesDescriptor parameterTypesDescriptor;
    
    protected ExecutableClassMemberDescriptor(final T executableMember) {
        super(executableMember);
        this.parameterTypesDescriptor = new ParameterTypesDescriptor(executableMember.getParameterTypes());
    }
    
    boolean isMatchParameters(final Object... parameters) {
        final ParameterTypesDescriptor parameterTypes = new ParameterTypesDescriptor(ClassUtils.toClass(parameters));
        return parameterTypes.isEqualParameterType(parameterTypesDescriptor);
    }
    
    int getParameterCount() {
        return parameterTypesDescriptor.getParameterCount();
    }
    
    Collection<Class<?>> getParameterTypes() {
        return parameterTypesDescriptor.getParameterTypes();
    }
}
