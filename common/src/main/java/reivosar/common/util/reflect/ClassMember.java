package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.annotation.Annotation;
import java.util.Collection;

class ClassMember extends Model {
    
    private final FieldDescriptors fieldDescriptors;
    private final ConstructorDescriptors constructorDescriptors;
    private final MethodDescriptors methodDescriptors;
    
    ClassMember(
            final FieldDescriptors fieldDescriptors,
            final ConstructorDescriptors constructorDescriptors,
            final MethodDescriptors methodDescriptors) {
        this.fieldDescriptors = fieldDescriptors;
        this.constructorDescriptors = constructorDescriptors;
        this.methodDescriptors = methodDescriptors;
    }
    
    Collection<String> getFieldNames() {
        return fieldDescriptors.getMemberNames();
    }
    
    Collection<FieldDescriptor> getFieldDescriptors() {
        return fieldDescriptors.getDescriptors();
    }
    
    Collection<FieldDescriptor> filterFieldDescriptor(final String name) {
        return fieldDescriptors.filter(name);
    }
    
    Collection<FieldDescriptor> filterFieldDescriptor(final Class<? extends Annotation> annotationClass) {
        return fieldDescriptors.filter(annotationClass);
    }
    
    Collection<String> getConstructorNames() {
        return constructorDescriptors.getMemberNames();
    }
    
    Collection<String> getConstructorDetails() {
        return constructorDescriptors.getMemberDetails();
    }
    
    Collection<ConstructorDescriptor> getConstructorDescriptors() {
        return constructorDescriptors.getDescriptors();
    }
    
    Collection<ConstructorDescriptor> filterConstructorDescriptor(final String name, final Object... parameters) {
        return constructorDescriptors.filter(name, parameters);
    }
    
    Collection<ConstructorDescriptor> filterConstructorDescriptor(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        return constructorDescriptors.filter(annotationClass, parameters);
    }
    
    Collection<String> getMethodNames() {
        return methodDescriptors.getMemberNames();
    }
    
    Collection<MethodDescriptor> getMethodMetadataDescriptors() {
        return methodDescriptors.getDescriptors();
    }
    
    Collection<MethodDescriptor> filterMethodDescriptor(final String name, final Object... parameters) {
        return methodDescriptors.filter(name, parameters);
    }
    
    Collection<MethodDescriptor> filterMethodDescriptor(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        return methodDescriptors.filter(annotationClass, parameters);
    }
}
