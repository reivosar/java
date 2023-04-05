package reivosar.common.util.reflect;

import reivosar.common.util.lang.ClassUtil;
import reivosar.common.util.model.Model;
import reivosar.common.util.reflect.member.*;
import reivosar.common.util.reflect.type.TypeDescriptor;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.stream.Collectors;

class DefaultClassDescriptor extends Model implements ClassDescriptor {
    
    private final Class<?> clazz;
    private final String packageName;
    private final String simpleName;
    private final String className;
    private final ClassModifier classModifier;
    private final FieldDescriptors fieldDescriptors;
    private final ConstructorDescriptors constructorDescriptors;
    private final MethodDescriptors methodDescriptors;
    
    DefaultClassDescriptor(final Class<?> clazz) {
        this.clazz = clazz;
        this.packageName = ClassUtil.getPackageName(clazz);
        this.simpleName = ClassUtil.getSimpleName(clazz);
        this.className = clazz.getName();
        this.classModifier = new ClassModifier(clazz);
        this.fieldDescriptors = FieldDescriptorsFactory.createDescriptors(clazz);
        this.constructorDescriptors = ConstructorDescriptorsFactory.createDescriptors(clazz);
        this.methodDescriptors = MethodDescriptorsFactory.createDescriptors(clazz);
    }
    
    @Override
    public Class<?> getRawClass() {
        return clazz;
    }
    
    @Override
    public String getPackageName() {
        return packageName;
    }
    
    @Override
    public String getSimpleName() {
        return simpleName;
    }
    
    @Override
    public String getName() {
        return className;
    }
    
    @Override
    public ClassModifier getClassModifier() {
        return classModifier;
    }
    
    @Override
    public FieldDescriptors getFieldDescriptors() {
        return fieldDescriptors;
    }
    
    @Override
    public ConstructorDescriptors getConstructorDescriptors() {
        return constructorDescriptors;
    }
    
    @Override
    public boolean hasConstructorWithAssignableParameterTypes(final Class<?>... parameterTypes) {
        return isAssignable(getConstructorDescriptors(), parameterTypes, false);
    }
    
    @Override
    public boolean hasConstructorWithMatchingParameterTypes(final Class<?>... parameterTypes) {
        return isAssignable(getConstructorDescriptors(), parameterTypes, true);
    }
    
    @Override
    public Optional<Object> newInstance(final Object... parameters) {
        return getConstructorDescriptors()
                .filter(parameters)
                .getDescriptors().stream().findFirst()
                .map(ConstructorDescriptor::getConstructorAccessor)
                .map(constructorAccessor -> constructorAccessor.newInstance(parameters));
    }
    
    private Collection<Class<?>> getTypeDescriptors(final ExecutableClassMemberDescriptor descriptor) {
        return descriptor.getParameterTypesDescriptor()
                .getParameterTypes().stream()
                .map(TypeDescriptor::getRawType)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
    
    @Override
    public boolean hasMethodWithAssignableParameterTypes(final Class<?>... parameterTypes) {
        return isAssignable(getMethodDescriptors(), parameterTypes, false);
    }
    
    @Override
    public boolean hasMethodWithMatchingParameterTypes(final Class<?>... parameterTypes) {
        return isAssignable(getMethodDescriptors(), parameterTypes, true);
    }
    
    @Override
    public MethodDescriptors getMethodDescriptors() {
        return methodDescriptors;
    }
    
    private boolean isAssignable(final ExecutableClassMemberDescriptors<?> descriptors,
                                 final Class<?>[] parameterTypes,
                                 final boolean strictComparison) {
        return descriptors.getDescriptors()
                .stream()
                .anyMatch(descriptor ->
                        ClassUtil.isAssignable(getTypeDescriptors(descriptor),
                                Arrays.stream(parameterTypes).toList(), strictComparison));
    }
}

