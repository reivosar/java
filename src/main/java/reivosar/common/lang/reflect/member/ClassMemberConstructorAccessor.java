package reivosar.common.lang.reflect.member;

import reivosar.common.lang.ConstructorUtil;
import reivosar.common.data.model.Model;

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
