package reivosar.common.util.reflect;

import java.util.Collection;

class ExecutableClassMemberCollectionAccessor<T extends ExecutableClassMemberDescriptor>
        extends ClassMemberMetadataCollectionAccessor<T> {
    
    protected ExecutableClassMemberCollectionAccessor(final Collection<T> ts) {
        super(ts);
    }
    
    Collection<T> filter(final Object... parameters) {
        return filter(t -> t.isMatchParameters(parameters));
    }
}
