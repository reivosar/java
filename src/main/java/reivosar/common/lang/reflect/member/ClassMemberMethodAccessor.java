package reivosar.common.lang.reflect.member;

import reivosar.common.lang.MethodUtil;
import reivosar.common.lang.ObjectUtil;
import reivosar.common.data.model.Model;

import java.lang.reflect.Method;

class ClassMemberMethodAccessor extends Model implements MethodAccessor {
    
    private final Method method;
    
    ClassMemberMethodAccessor(final Method method, final boolean forceAccess) {
        this.method = method;
        this.method.setAccessible(forceAccess);
    }
    
    @Override
    public Object invokeMethod(final Object target, final Object... parameters) {
        ObjectUtil.requireNonNull("target", target);
        return MethodUtil.invokeMethod(method, target, parameters);
    }
    
    @Override
    public Object invokeStaticMethod(final Object... parameters) {
        return MethodUtil.invokeStaticMethod(method, parameters);
    }
}
