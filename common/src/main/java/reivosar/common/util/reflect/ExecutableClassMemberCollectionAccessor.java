package reivosar.common.util.reflect;

import reivosar.common.util.lang.ArrayUtil;

import java.lang.annotation.Annotation;
import java.util.Collection;

class ExecutableClassMemberCollectionAccessor<T extends ExecutableClassMemberDescriptor>
        extends ClassMemberMetadataCollectionAccessor<T> {
    
    protected ExecutableClassMemberCollectionAccessor(final Collection<T> ts) {
        super(ts);
    }
    
    Collection<T> filter(final String name, final Object... parameters) {
        return filterWithParameters(filter(name), parameters);
    }
    
    Collection<T> filter(final Class<? extends Annotation> annotationClass, final Object... parameters) {
        return filterWithParameters(filter(annotationClass), parameters);
    }
    
    private Collection<T> filterWithParameters(final Collection<T> collection, final Object[] parameters) {
        if (ArrayUtil.isNotEmpty(parameters)) {
            return filter(collection, t -> t.isMatchParameters(parameters));
        }
        return collection;
    }
}
