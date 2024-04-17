package reivosar.common.lang.reflect.member;

import reivosar.common.lang.ObjectUtil;

import java.lang.reflect.Method;

/**
 * A factory for creating {@link MethodDescriptor} objects from {@link Method} objects.
 */
public class MethodDescriptorFactory {
    
    /**
     * Creates a new {@link MethodDescriptor} object for the specified Method.
     *
     * @param method the Method for which to create the descriptor
     * @return a new {@link MethodDescriptor} object for the specified Method
     * @throws NullPointerException if {@code Method} is {@code null}
     */
    public static MethodDescriptor create(final Method method) {
        ObjectUtil.requireNonNull("method", method);
        return new ClassMemberMethodDescriptor(method, true);
    }
}
