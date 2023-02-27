package reivosar.common.util.reflect.member;

import reivosar.common.util.lang.ArrayUtil;
import reivosar.common.util.lang.ObjectUtil;

import java.lang.annotation.Annotation;
import java.util.Collection;

@SuppressWarnings("unchecked")
class CollectedExecutableClassMemberDescriptors<T extends ExecutableClassMemberDescriptor,
        S extends ExecutableClassMemberDescriptors<T, S>>
        extends CollectedMetadataDescriptors<T, S>
        implements ExecutableClassMemberDescriptors<T, S> {
    
    protected CollectedExecutableClassMemberDescriptors(final Collection<T> descriptors) {
        super(descriptors);
    }
    
    @Override
    public S filterWithParameters(final String name, final Object... parameters) {
        ObjectUtil.requireNonNull("name", name);
        return filterWithParameters(filter(getDescriptors(), t -> t.getName().equals(name)), parameters);
    }
    
    @Override
    public S filterWithParameters(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        ObjectUtil.requireNonNull("annotationClass", annotationClass);
        return filterWithParameters(filter(getDescriptors(), t -> t.hasAnnotation(annotationClass)), parameters);
    }
    
    private S filterWithParameters(final Collection<T> collection, final Object[] parameters) {
        Collection<T> result = collection;
        if (ArrayUtil.isNotEmpty(parameters)) {
            result = filter(result, t -> t.getParameterTypesDescriptor().isEqualParameterType(parameters));
        }
        return (S) new CollectedExecutableClassMemberDescriptors<T, S>(result);
    }
}
