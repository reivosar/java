package reivosar.common.util.reflect;

import java.lang.reflect.Method;

class MethodDescriptor extends ExecutableClassMemberDescriptor<Method> {
    
    private final MethodInvoker methodInvoker;
    
    MethodDescriptor(final Method method) {
        super(method);
        this.methodInvoker = new MethodInvoker(method);
    }
    
    Object invokeMethod(final Object target, final Object... parameters) {
        return methodInvoker.invokeMethod(target, parameters);
    }
    
    Object invokeStaticMethod(final Object... parameters) {
        return methodInvoker.invokeStaticMethod(parameters);
    }
}
