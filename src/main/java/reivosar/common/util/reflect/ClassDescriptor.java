package reivosar.common.util.reflect;

import reivosar.common.util.reflect.member.*;

import java.util.Optional;

/**
 * This interface represents a class descriptor, which provides information about a Java class.
 */
public interface ClassDescriptor {
    
    /**
     * Returns the raw {@link Class} object associated with this descriptor.
     *
     * @return the raw {@code Class} object
     */
    Class<?> getRawClass();
    
    /**
     * Returns the package name of the class.
     *
     * @return the package name
     */
    String getPackageName();
    
    /**
     * Returns the simple name of the class.
     *
     * @return the simple name
     */
    String getSimpleName();
    
    /**
     * Returns the fully qualified name of the class.
     *
     * @return the fully qualified name
     */
    String getName();
    
    /**
     * Returns the class modifier of the class.
     *
     * @return the class modifier
     */
    ClassModifier getClassModifier();
    
    /**
     * Returns the field descriptors of the class.
     *
     * @return the field descriptors
     */
    FieldDescriptors getFieldDescriptors();
    
    /**
     * Returns the constructor descriptors of the class.
     *
     * @return the constructor descriptors
     */
    ConstructorDescriptors getConstructorDescriptors();
    
    /**
     * Returns whether the class has a constructor that can be invoked with the given parameter types.
     *
     * @param parameterTypes the types of the parameters to match
     * @return true if a constructor with matching parameter types is found, false otherwise
     */
    boolean hasConstructorWithAssignableParameterTypes(Class<?>... parameterTypes);
    
    /**
     * Returns whether the class has a constructor that can be invoked with the given parameter types,
     * using exact matching.
     *
     * @param parameterTypes the types of the parameters to match exactly
     * @return true if a constructor with exactly matching parameter types is found, false otherwise
     */
    boolean hasConstructorWithMatchingParameterTypes(Class<?>... parameterTypes);
    
    /**
     * Returns a new instance of the class with the given parameters by invoking the constructor with matching parameter types.
     *
     * @param parameters the parameters to pass to the constructor
     * @return a new instance of the class with the given parameters
     */
    Optional<Object> newInstance(Object... parameters);
    
    /**
     * Returns whether the class has a method that can be invoked with the given parameter types.
     *
     * @param parameterTypes the types of the parameters to match
     * @return true if a method with matching parameter types is found, false otherwise
     */
    boolean hasMethodWithAssignableParameterTypes(Class<?>... parameterTypes);
    
    /**
     * Returns whether the class has a method that can be invoked with the given parameter types,
     * using exact matching.
     *
     * @param parameterTypes the types of the parameters to match exactly
     * @return true if a method with exactly matching parameter types is found, false otherwise
     */
    boolean hasMethodWithMatchingParameterTypes(Class<?>... parameterTypes);
    
    /**
     * Returns the method descriptors of the class.
     *
     * @return the method descriptors
     */
    MethodDescriptors getMethodDescriptors();
}