package reivosar.common.util.reflect.member;

import reivosar.common.util.reflect.type.ParameterTypesDescriptor;

/**
 * This interface defines the descriptor for an executable member of a class, such as a method or a constructor.
 * <p>
 * It extends {@link ClassMemberDescriptor} and {@link MetadataDescriptor}.
 */
public interface ExecutableClassMemberDescriptor extends ClassMemberDescriptor, MetadataDescriptor {
    
    /**
     * Returns the parameter types descriptor for this executable member.
     *
     * @return the parameter types descriptor
     */
    ParameterTypesDescriptor getParameterTypesDescriptor();
    
    /**
     * Checks whether the parameter types are equal to those of the specified descriptor.
     *
     * @param descriptor the descriptor to compare to
     * @return true if the parameter types are equal, false otherwise
     */
    default boolean matchParameterType(final ParameterTypesDescriptor descriptor) {
        return getParameterTypesDescriptor().matchParameterType(descriptor);
    }
    
    /**
     * Checks whether the parameter types are equal to those of the specified descriptor.
     *
     * @param parameterTypes the descriptor to compare to
     * @return true if the parameter types are equal, false otherwise
     */
    default boolean matchParameterType(final Class<?>... parameterTypes) {
        return getParameterTypesDescriptor().matchParameterType(parameterTypes);
    }
    
    /**
     * Checks whether the parameter types are equal to those of the specified descriptor.
     *
     * @param parameters the descriptor to compare to
     * @return true if the parameter types are equal, false otherwise
     */
    default boolean matchParameterType(final Object... parameters) {
        return getParameterTypesDescriptor().matchParameterType(parameters);
    }
    
}