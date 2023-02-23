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
        final FieldDescriptors fieldDescriptors = getFieldDescriptors();
        final ConstructorDescriptors constructorDescriptors = getConstructorDescriptors();
        final MethodDescriptors methodDescriptors = getMethodDescriptors();
        return new ClassMember(fieldDescriptors, constructorDescriptors, methodDescriptors);
    }
    
    private FieldDescriptors getFieldDescriptors() {
        try {
            return new FieldDescriptors(aClass.getDeclaredFields());
        } catch (Throwable e) {
            return new FieldDescriptors(null);
        }
    }
    
    private ConstructorDescriptors getConstructorDescriptors() {
        try {
            return new ConstructorDescriptors(aClass.getDeclaredConstructors());
        } catch (Throwable e) {
            return new ConstructorDescriptors(null);
        }
    }
    
    private MethodDescriptors getMethodDescriptors() {
        try {
            return new MethodDescriptors(aClass.getDeclaredMethods());
        } catch (Throwable e) {
            return new MethodDescriptors(null);
        }
    }
}
