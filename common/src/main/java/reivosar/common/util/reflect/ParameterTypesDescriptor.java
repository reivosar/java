package reivosar.common.util.reflect;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ClassUtils;
import reivosar.common.util.model.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

class ParameterTypesDescriptor extends Model {
    
    private final Class<?>[] parameterTypes;
    
    ParameterTypesDescriptor(final Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
    
    int getParameterCount() {
        return getParameterTypes().size();
    }
    
    boolean isEqualParameterType(final ParameterTypesDescriptor descriptor) {
        return CollectionUtils.isEqualCollection(getParameterTypes(), descriptor.getParameterTypes());
    }
    
    Collection<Class<?>> getParameterTypes() {
        return Arrays.stream(this.parameterTypes)
                .map(ClassUtils::primitiveToWrapper)
                .collect(Collectors.toList());
    }
}
