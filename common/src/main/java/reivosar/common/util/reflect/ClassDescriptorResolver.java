package reivosar.common.util.reflect;

class ClassDescriptorResolver {
    
    private final Class<?> aClass;
    
    ClassDescriptorResolver(final Class<?> aClass) {
        this.aClass = aClass;
    }
    
    ClassProfile getClassProfile() {
        return new ClassProfile(aClass);
    }
    
    FieldDescriptors getFieldDescriptors() {
        try {
            return new FieldDescriptors(aClass.getDeclaredFields());
        } catch (Throwable e) {
            return new FieldDescriptors(null);
        }
    }
    
    ConstructorDescriptors getConstructorDescriptors() {
        try {
            return new ConstructorDescriptors(aClass.getDeclaredConstructors());
        } catch (Throwable e) {
            return new ConstructorDescriptors(null);
        }
    }
    
    MethodDescriptors getMethodDescriptors() {
        try {
            return new MethodDescriptors(aClass.getDeclaredMethods());
        } catch (Throwable e) {
            return new MethodDescriptors(null);
        }
    }
}
