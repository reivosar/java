package reivosar.common.util.reflect;

import reivosar.common.util.lang.ArrayUtil;

import java.lang.reflect.Method;

class MethodInvoker {
    
    private final Method method;
    
    MethodInvoker(final Method method) {
        this.method = method;
        this.method.setAccessible(true);
    }
    
    Object invokeMethod(final Object target, final Object... parameters) {
        try {
            return method.invoke(target, ArrayUtil.nullToEmpty(parameters));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    Object invokeStaticMethod(final Object... parameters) {
        try {
            return method.invoke(null, ArrayUtil.nullToEmpty(parameters));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
