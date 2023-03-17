package reivosar.common.util.reflect.type;

import reivosar.common.util.collection.CollectionUtil;
import reivosar.common.util.lang.ClassUtil;
import reivosar.common.util.model.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

abstract class AbstractParameterTypesDescriptor extends Model implements ParameterTypesDescriptor {
    
    private final Class<?>[] parameterTypes;
    
    protected AbstractParameterTypesDescriptor(final Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }
    
    @Override
    public int getParameterCount() {
        return getParameterTypes().size();
    }
    
    @Override
    public boolean matchParameterType(final ParameterTypesDescriptor descriptor) {
        return CollectionUtil.isSameOrderCollection(getParameterTypes(), descriptor.getParameterTypes());
    }
    
    @Override
    public boolean matchParameterType(final Class<?>... parameterTypes) {
        return CollectionUtil.isSameOrderCollection(getParameterTypes(), toTypeDescriptor(parameterTypes));
    }
    
    @Override
    public boolean matchParameterType(final Object... parameters) {
        return CollectionUtil.isSameOrderCollection(getParameterTypes(), toTypeDescriptor(ClassUtil.toClass(parameters)));
    }
    
    @Override
    public Collection<TypeDescriptor> getParameterTypes() {
        return toTypeDescriptor(this.parameterTypes);
    }
    
    private Collection<TypeDescriptor> toTypeDescriptor(final Class<?>[] parameterTypes) {
        return Arrays.stream(parameterTypes)
                .map(ClassUtil::primitiveToWrapper)
                .map(ClassTypeDescriptor::new)
                .collect(Collectors.toList());
    }
}
