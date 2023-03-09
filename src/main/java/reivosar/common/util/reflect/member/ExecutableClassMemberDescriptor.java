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
}