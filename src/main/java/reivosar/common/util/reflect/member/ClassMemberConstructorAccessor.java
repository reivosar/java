package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ConstructorUtil;
import reivosar.common.util.model.Model;

import java.lang.reflect.Constructor;

class ClassMemberConstructorAccessor extends Model implements ConstructorAccessor {
    
    private final Constructor<?> constructor;
    
    ClassMemberConstructorAccessor(final Constructor<?> constructor, final boolean forceAccess) {
        this.constructor = constructor;
        this.constructor.setAccessible(forceAccess);
    }
    
    @Override
    public <T> T newInstance(final Object... parameters) {
        return ConstructorUtil.newInstance(constructor, parameters);
    }
}
