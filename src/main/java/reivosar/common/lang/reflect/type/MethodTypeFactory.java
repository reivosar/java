package reivosar.common.lang.reflect.type;

import java.lang.reflect.Method;

/**
 * A factory for creating method type descriptors for a given {@link Method} object.
 */
public class MethodTypeFactory {
    
    /**
     * Creates a new {@link ReturnTypeDescriptor} for the given method.
     *
     * @param method the method to append a return type descriptor for
     * @return a new return type descriptor for the given method
     */
    public static ReturnTypeDescriptor createReturnType(final Method method) {
        return new MethodReturnTypeDescriptor(method);
    }
    
    /**
     * Creates a new {@link ParameterTypesDescriptor} for the given method.
     *
     * @param method the method to append a parameter types descriptor for
     * @return a new parameter types descriptor for the given method
     */
    public static ParameterTypesDescriptor createParameterTypes(final Method method) {
        return new MethodParameterTypesDescriptor(method);
    }
}
