package reivosar.common.util.reflect;

class ClassDescriptorResolver {
    
    private final Class<?> aClass;
    
    ClassDescriptorResolver(final Class<?> aClass) {
        this.aClass = aClass;
    }
    
    ClassProfile resolverClassProfile() {
        return new ClassProfile(aClass);
    }
    
    ClassMember resolverClassMember() {
        final FieldMetadataDescriptors fieldDescriptors = getFieldDescriptors();
        final ConstructorMetadataDescriptors constructorDescriptors = getConstructorDescriptors();
        final MethodMetadataDescriptors methodDescriptors = getMethodDescriptors();
        return new ClassMember(fieldDescriptors, constructorDescriptors, methodDescriptors);
    }
    
    private FieldMetadataDescriptors getFieldDescriptors() {
        try {
            return new FieldMetadataDescriptors(aClass.getDeclaredFields());
        } catch (Throwable e) {
            return new FieldMetadataDescriptors(null);
        }
    }
    
    private ConstructorMetadataDescriptors getConstructorDescriptors() {
        try {
            return new ConstructorMetadataDescriptors(aClass.getDeclaredConstructors());
        } catch (Throwable e) {
            return new ConstructorMetadataDescriptors(null);
        }
    }
    
    private MethodMetadataDescriptors getMethodDescriptors() {
        try {
            return new MethodMetadataDescriptors(aClass.getDeclaredMethods());
        } catch (Throwable e) {
            return new MethodMetadataDescriptors(null);
        }
    }
}
