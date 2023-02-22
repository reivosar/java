package reivosar.common.util.reflect;

import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Constructor;

@SuppressWarnings("unchecked")
class ConstructorInvoker {
    
    private final Constructor<?> constructor;
    
    ConstructorInvoker(final Constructor<?> constructor) {
        this.constructor = constructor;
        this.constructor.setAccessible(true);
    }
    
    <T> T newInstance(final Object... parameters) {
        try {
            return (T) this.constructor.newInstance(ArrayUtils.nullToEmpty(parameters));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
