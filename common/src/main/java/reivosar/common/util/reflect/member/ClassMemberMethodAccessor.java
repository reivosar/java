package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.MethodUtil;
import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.lang.reflect.Method;

class ClassMemberMethodAccessor extends Model implements MethodAccessor {
    
    private final Method method;
    
    ClassMemberMethodAccessor(final Method method) {
        this.method = method;
        this.method.setAccessible(true);
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
