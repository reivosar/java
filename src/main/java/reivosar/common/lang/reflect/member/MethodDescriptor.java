package reivosar.common.lang.reflect.member;

import reivosar.common.lang.reflect.type.ReturnTypeDescriptor;

/**
 * A descriptor for a method, providing information about its access scope,
 * return type, name, parameter types, metadata, and method accessor.
 */
public interface MethodDescriptor extends ExecutableClassMemberDescriptor {
    
    /**
     * Returns the descriptor for the return type of the method.
     *
     * @return the descriptor for the return type of the method
     */
    ReturnTypeDescriptor getReturnTypeDescriptor();
    
    /**
     * Returns the method accessor for the method.
     *
     * @return the method accessor for the method
     */
    MethodAccessor getMethodAccessor();
}