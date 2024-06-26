package reivosar.common.lang.reflect.type;

import reivosar.common.lang.ClassUtil;
import reivosar.common.lang.ObjectUtil;
import reivosar.common.data.model.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

abstract class AbstractParameterTypesDescriptor extends Model implements ParameterTypesDescriptor {
    
    private final Class<?>[] parameterTypes;
    
    protected AbstractParameterTypesDescriptor(final Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
    
    @Override
    public int getParameterCount() {
        return getParameterTypes().size();
    }
    
    @Override
    public boolean isMatchParameterType(final ParameterTypesDescriptor descriptor) {
        ObjectUtil.requireNonNull("descriptor", descriptor);
        return isMatchParameterType(descriptor.getParameterTypes());
    }
    
    @Override
    public boolean isMatchParameterType(final Class<?>... parameterTypes) {
        ObjectUtil.requireNonNull("parameterTypes", parameterTypes);
        return isAssignable(parameterTypes, true);
    }
    
    @Override
    public boolean isMatchParameterType(final Object... parameters) {
        ObjectUtil.requireNonNull("parameters", parameters);
        return isAssignable(ClassUtil.toClass(parameters), true);
    }
    
    @Override
    public boolean isMatchAssignableParameterType(final ParameterTypesDescriptor descriptor) {
        ObjectUtil.requireNonNull("descriptor", descriptor);
        return isMatchAssignableParameterType(descriptor.getParameterTypes());
    }
    
    @Override
    public boolean isMatchAssignableParameterType(final Class<?>... parameterTypes) {
        ObjectUtil.requireNonNull("parameterTypes", parameterTypes);
        return isAssignable(parameterTypes, false);
    }
    
    @Override
    public boolean isMatchAssignableParameterType(final Object... parameters) {
        ObjectUtil.requireNonNull("parameters", parameters);
        return isAssignable(ClassUtil.toClass(parameters), false);
    }
    
    private boolean isAssignable(final Class<?>[] parameterTypes, final boolean strictComparison) {
        return ClassUtil.isAssignable(toTypeClasses(toTypeDescriptor(parameterTypes)),
                toTypeClasses(getParameterTypes()), strictComparison);
    }
    
    private Collection<Class<?>> toTypeClasses(final Collection<TypeDescriptor> descriptors) {
        return descriptors.stream().map(TypeDescriptor::getRawType).collect(Collectors.toList());
    }
    
    @Override
    public Collection<TypeDescriptor> getParameterTypes() {
        return toTypeDescriptor(this.parameterTypes);
    }
    
    private Collection<TypeDescriptor> toTypeDescriptor(final Class<?>[] parameterTypes) {
        return Arrays.stream(parameterTypes)
                .map(ClassUtil::primitiveToWrapper)
                .map(ClassTypeDescriptor::new)
                .collect(Collectors.toList());
    }
}
