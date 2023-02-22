package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.util.Collection;

/**
 * A descriptor for a class, providing information about its package,
 * name, access scope, fields, constructors, and methods.
 */
public class ClassDescriptor extends Model {
    
    private final ClassProfile classProfile;
    private final FieldMetadataDescriptors fieldDescriptors;
    private final ConstructorMetadataDescriptors constructorDescriptors;
    private final MethodMetadataDescriptors methodDescriptors;
    
    /**
     * Constructs a new {@code ClassDescriptor} object for the specified class.
     *
     * @param aClass the class for which to construct the descriptor
     * @throws NullPointerException if {@code aClass} is {@code null}
     */
    public ClassDescriptor(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        final ClassDescriptorResolver resolver = new ClassDescriptorResolver(aClass);
        this.classProfile = resolver.getClassProfile();
        this.fieldDescriptors = resolver.getFieldDescriptors();
        this.constructorDescriptors = resolver.getConstructorDescriptors();
        this.methodDescriptors = resolver.getMethodDescriptors();
    }
    
    /**
     * Returns the package name of the class.
     *
     * @return the package name of the class
     */
    public String getPackageName() {
        return classProfile.getPackageName();
    }
    
    /**
     * Returns the name of the class.
     *
     * @return the name of the class
     */
    public String getClassName() {
        return classProfile.getClassName();
    }
    
    /**
     * Returns the access scope of the class.
     *
     * @return the access scope of the class
     */
    public AccessScope getAccessScope() {
        return classProfile.getAccessScope();
    }
    
    /**
     * Returns the names of the fields in the class.
     *
     * @return a collection of field names
     */
    public Collection<String> getFieldNames() {
        return fieldDescriptors.names();
    }
    
    /**
     * Returns the names of the constructors in the class.
     *
     * @return a collection of constructor names
     */
    public Collection<String> getConstructorNames() {
        return constructorDescriptors.names();
    }
    
    /**
     * Returns the names of the methods in the class.
     *
     * @return a collection of method names
     */
    public Collection<String> getMethodNames() {
        return methodDescriptors.names();
    }
}
