package reivosar.common.util.reflect;

import reivosar.common.util.lang.ConstructorUtil;
import reivosar.common.util.model.Model;

import java.lang.reflect.Constructor;

class ConstructorExecutor extends Model {
    
    private final Constructor<?> constructor;
    
    ConstructorExecutor(final Constructor<?> constructor) {
        this.constructor = constructor;
        this.constructor.setAccessible(true);
    }
    
    <T> T newInstance(final Object... parameters) {
        return ConstructorUtil.newInstance(constructor, parameters);
    }
}
