package reivosar.common.lang.reflect.type;

import java.util.Collection;

/**
 * This interface represents a descriptor of the parameter types for a method or constructor.
 */
public interface ParameterTypesDescriptor {
    
    /**
     * Returns the number of parameters.
     *
     * @return the number of parameters
     */
    int getParameterCount();
    
    /**
     * Tests whether the parameter types of this descriptor match those of another descriptor exactly.
     *
     * @param descriptor the descriptor to test against
     * @return {@code true} if the parameter types match exactly, otherwise {@code false}
     */
    boolean isMatchParameterType(final ParameterTypesDescriptor descriptor);
    
    /**
     * Tests whether the parameter types of this descriptor match those specified in the given array exactly.
     *
     * @param parameterTypes the array of parameter types to test against
     * @return {@code true} if the parameter types match exactly, otherwise {@code false}
     */
    boolean isMatchParameterType(final Class<?>... parameterTypes);
    
    /**
     * Tests whether the parameter types of this descriptor match those of the given parameters exactly.
     *
     * @param parameters the array of parameters to test against
     * @return {@code true} if the parameter types match exactly, otherwise {@code false}
     */
    boolean isMatchParameterType(final Object... parameters);
    
    /**
     * Tests whether the parameter types of this descriptor are assignable from those of another descriptor.
     *
     * @param descriptor the descriptor to test against
     * @return {@code true} if the parameter types are assignable, otherwise {@code false}
     */
    boolean isMatchAssignableParameterType(final ParameterTypesDescriptor descriptor);
    
    /**
     * Tests whether the parameter types of this descriptor are assignable from those specified in the given array.
     *
     * @param parameterTypes the array of parameter types to test against
     * @return {@code true} if the parameter types are assignable, otherwise {@code false}
     */
    boolean isMatchAssignableParameterType(final Class<?>... parameterTypes);
    
    /**
     * Tests whether the parameter types of this descriptor are assignable from the given parameters.
     *
     * @param parameters the array of parameters to test against
     * @return {@code true} if the parameter types are assignable, otherwise {@code false}
     */
    boolean isMatchAssignableParameterType(final Object... parameters);
    
    /**
     * Returns a collection of TypeDescriptors representing the parameter types of this descriptor.
     *
     * @return a collection of TypeDescriptors representing the parameter types
     */
    Collection<TypeDescriptor> getParameterTypes();
}