package reivosar.common.util.reflect;

class ClassDescriptorResolver {
    
    private final Class<?> aClass;
    
    ClassDescriptorResolver(final Class<?> aClass) {
        this.aClass = aClass;
    }
    
    ClassProfile getClassProfile() {
        return new ClassProfile(aClass);
    }
    
    FieldMetadataDescriptors getFieldDescriptors() {
        try {
            return new FieldMetadataDescriptors(aClass.getDeclaredFields());
        } catch (Throwable e) {
            return new FieldMetadataDescriptors(null);
        }
    }
    
    ConstructorMetadataDescriptors getConstructorDescriptors() {
        try {
            return new ConstructorMetadataDescriptors(aClass.getDeclaredConstructors());
        } catch (Throwable e) {
            return new ConstructorMetadataDescriptors(null);
        }
    }
    
    MethodMetadataDescriptors getMethodDescriptors() {
        try {
            return new MethodMetadataDescriptors(aClass.getDeclaredMethods());
        } catch (Throwable e) {
            return new MethodMetadataDescriptors(null);
        }
    }
}
