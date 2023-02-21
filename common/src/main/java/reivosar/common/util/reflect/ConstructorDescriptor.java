package reivosar.common.util.reflect;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")
class ConstructorDescriptor extends ClassMemberDescriptor {
    
    private final Constructor<?> constructor;
    
    ConstructorDescriptor(final Constructor<?> constructor) {
        super(constructor.getName(), constructor.getModifiers(), constructor);
        this.constructor = constructor;
        this.constructor.setAccessible(true);
    }
    
    boolean isMatchParameters(final Object... parameters) {
        final List<Class<?>> parameterTypes = Arrays.asList(ClassUtils.toClass(parameters));
        final List<Class<?>> constructorTypes = Arrays.stream(this.constructor.getParameterTypes())
                .map(ClassUtils::primitiveToWrapper)
                .collect(Collectors.toList());
        return CollectionUtils.isEqualCollection(parameterTypes, constructorTypes);
    }
    
    <T> T newInstance(final Object... parameters) {
        try {
            return (T) this.constructor.newInstance(ArrayUtils.nullToEmpty(parameters));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
    }
}
