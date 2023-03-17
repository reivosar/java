package reivosar.common.util.reflect.type;

import java.util.Collection;

/**
 * Represents a descriptor for the parameter types of a method or constructor.
 */
public interface ParameterTypesDescriptor {
    
    /**
     * Returns the number of parameters.
     *
     * @return the number of parameters
     */
    int getParameterCount();
    
    /**
     * Checks whether the parameter types are equal to those of the specified descriptor.
     *
     * @param descriptor the descriptor to compare to
     * @return true if the parameter types are equal, false otherwise
     */
    boolean matchParameterType(final ParameterTypesDescriptor descriptor);
    
    /**
     * Checks whether the parameter types are equal to those of the specified descriptor.
     *
     * @param parameterTypes the descriptor to compare to
     * @return true if the parameter types are equal, false otherwise
     */
    boolean matchParameterType(final Class<?>... parameterTypes);
    
    /**
     * Checks whether the parameter types are equal to those of the specified descriptor.
     *
     * @param parameters the descriptor to compare to
     * @return true if the parameter types are equal, false otherwise
     */
    boolean matchParameterType(final Object... parameters);
    
    /**
     * Returns the parameter types.
     *
     * @return a collection of parameter types
     */
    Collection<TypeDescriptor> getParameterTypes();
}