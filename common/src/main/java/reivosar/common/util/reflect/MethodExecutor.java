package reivosar.common.util.reflect;

import reivosar.common.util.lang.MethodUtil;

import java.lang.reflect.Method;

class MethodExecutor {
    
    private final Method method;
    
    MethodExecutor(final Method method) {
        this.method = method;
        this.method.setAccessible(true);
    }
    
    Object invokeMethod(final Object target, final Object... parameters) {
        return MethodUtil.invokeMethod(method, target, parameters);
    }
    
    Object invokeStaticMethod(final Object... parameters) {
        return MethodUtil.invokeStaticMethod(method, parameters);
    }
}
