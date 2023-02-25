package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.util.Collection;

/**
 * A descriptor for a class, providing information about its package,
 * name, access scope, fields, constructors, and methods.
 */
public class ClassDescriptor extends Model {
    
    private final ClassMember classMember;
    
    /**
     * Constructs a new {@code ClassDescriptor} object for the specified class.
     *
     * @param aClass the class for which to construct the descriptor
     * @throws NullPointerException if {@code aClass} is {@code null}
     */
    public ClassDescriptor(final Class<?> aClass) {
        ObjectUtil.requireNonNull("aClass", aClass);
        final ClassDescriptorResolver resolver = new ClassDescriptorResolver(aClass);
        this.classMember = resolver.resolverClassMember();
    }
    
    /**
     * Returns the names of the fields in the class.
     *
     * @return a collection of field names
     */
    public Collection<String> getFieldNames() {
        return classMember.getFieldNames();
    }
    
    /**
     * Returns the names of the constructors in the class.
     *
     * @return a collection of constructor names
     */
    public Collection<String> getConstructorNames() {
        return classMember.getConstructorNames();
    }
    
    /**
     * Returns the names of the methods in the class.
     *
     * @return a collection of method names
     */
    public Collection<String> getMethodNames() {
        return classMember.getMethodNames();
    }
}
