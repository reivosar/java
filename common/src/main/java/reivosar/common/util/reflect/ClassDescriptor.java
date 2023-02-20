package reivosar.common.util.reflect;

import org.apache.commons.lang3.reflect.FieldUtils;
import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

public class ClassDescriptor extends Model {
    
    private final ClassProfile classProfile;
    private final FieldDescriptors fieldDescriptors;
    private final ConstructorDescriptors constructorDescriptors;
    private final MethodDescriptors methodDescriptors;
    
    public ClassDescriptor(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        final ClassDescriptorResolver resolver = new ClassDescriptorResolver(aClass);
        this.classProfile = resolver.getClassProfile();
        this.fieldDescriptors = resolver.getFieldDescriptors();
        this.constructorDescriptors = resolver.getConstructorDescriptors();
        this.methodDescriptors = resolver.getMethodDescriptors();
    }
    
    public String getPackageName() {
        return classProfile.getPackageName();
    }
    
    public String getClassName() {
        return classProfile.getClassName();
    }
    
    public FieldDescriptors getFieldDescriptors() {
        return fieldDescriptors;
    }
    
    public ConstructorDescriptors getConstructorDescriptors() {
        return constructorDescriptors;
    }
    
    public MethodDescriptors getMethodDescriptors() {
        return methodDescriptors;
    }
}
