package reivosar.common.lang.reflect.member;

import java.util.Collection;

abstract class CollectedExecutableClassMemberDescriptors<T extends ExecutableClassMemberDescriptor>
        extends CollectedMetadataDescriptors<T> implements ExecutableClassMemberDescriptors<T> {
    
    protected CollectedExecutableClassMemberDescriptors(final Collection<T> descriptors) {
        super(descriptors);
    }
}
