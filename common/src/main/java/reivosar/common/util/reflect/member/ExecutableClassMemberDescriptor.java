package reivosar.common.util.reflect.member;

import reivosar.common.util.reflect.type.ParameterTypesDescriptor;

public interface ExecutableClassMemberDescriptor {
    
    /**
     * Returns the descriptor for the parameter types of the method.
     *
     * @return the descriptor for the parameter types of the method
     */
    ParameterTypesDescriptor getParameterTypesDescriptor();
    
}
