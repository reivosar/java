package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

public class ClassDescriptor extends Model {
    
    private final ClassProfile classProfile;
    private final FieldDescriptors fieldDescriptors;
    private final ConstructorDescriptors constructorDescriptors;
    private final MethodDescriptors methodDescriptors;
    
    public ClassDescriptor(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        this.classProfile = new ClassProfile(aClass);
        this.fieldDescriptors = new FieldDescriptors(aClass);
        this.constructorDescriptors = new ConstructorDescriptors(aClass);
        this.methodDescriptors = new MethodDescriptors(aClass);
    }
    
    public ClassProfile classProfile() {
        return classProfile;
    }
    
    public FieldDescriptors fieldDescriptors() {
        return fieldDescriptors;
    }
    
    public ConstructorDescriptors constructorDescriptors() {
        return constructorDescriptors;
    }
    
    public MethodDescriptors methodDescriptors() {
        return methodDescriptors;
    }
}
