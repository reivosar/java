package reivosar.common.util.reflect;

import java.lang.reflect.Member;
import java.lang.reflect.Method;

class MethodDescriptor extends ClassMemberDescriptor {
    
    private final Method method;
    
    MethodDescriptor(final Method method) {
        this.method = method;
    }
    
    @Override
    protected Member getMember() {
        return method;
    }
    
    @Override
    protected ClassAccessibleObject getClassAccessibleObject() {
        return new ClassAccessibleObject(this.method);
    }
}
