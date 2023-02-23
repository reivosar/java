package reivosar.common.util.reflect;

import java.lang.reflect.Method;

public class MethodDescriptor extends ExecutableClassMemberDescriptor {
    
    private final MethodExecutor methodExecutor;
    
    MethodDescriptor(final Method method) {
        super(method);
        this.methodExecutor = new MethodExecutor(method);
    }
    
    public Object executeMethod(final Object target, final Object... parameters) {
        return methodExecutor.invokeMethod(target, parameters);
    }
    
    public Object executeStaticMethod(final Object... parameters) {
        return methodExecutor.invokeStaticMethod(parameters);
    }
}
