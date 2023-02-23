package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.util.Collection;

class ClassMember extends Model {
    
    private final FieldMetadataDescriptors fieldDescriptors;
    private final ConstructorMetadataDescriptors constructorDescriptors;
    private final MethodMetadataDescriptors methodDescriptors;
    
    ClassMember(
            final FieldMetadataDescriptors fieldDescriptors,
            final ConstructorMetadataDescriptors constructorDescriptors,
            final MethodMetadataDescriptors methodDescriptors) {
        this.fieldDescriptors = fieldDescriptors;
        this.constructorDescriptors = constructorDescriptors;
        this.methodDescriptors = methodDescriptors;
    }
    
    Collection<String> getFieldNames() {
        return fieldDescriptors.getMemberNames();
    }
    
    Collection<String> getConstructorNames() {
        return constructorDescriptors.getMemberNames();
    }
    
    Collection<String> getMethodNames() {
        return methodDescriptors.getMemberNames();
    }
}
