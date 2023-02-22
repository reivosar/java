package reivosar.common.util.reflect;

import reivosar.common.util.collection.CollectionUtil;
import reivosar.common.util.lang.ClassUtil;
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
        return CollectionUtil.isEqualCollection(getParameterTypes(), descriptor.getParameterTypes());
    }
    
    Collection<Class<?>> getParameterTypes() {
        return Arrays.stream(this.parameterTypes)
                .map(ClassUtil::primitiveToWrapper)
                .collect(Collectors.toList());
    }
}
