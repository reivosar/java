package reivosar.common.util.reflect.type;

import reivosar.common.util.model.Model;

import java.lang.reflect.Method;

class MethodReturnTypeDescriptor extends Model implements ReturnTypeDescriptor {
    
    private final Method method;
    
    MethodReturnTypeDescriptor(final Method method) {
        this.method = method;
    }
    
    @Override
    public String getName() {
        return method.getName();
    }
    
    @Override
    public Class<?> getRawType() {
        return method.getReturnType();
    }
}
