package reivosar.common.lang.reflect.member;

import java.util.Collection;

abstract class CollectedMetadataDescriptors<T extends ClassMemberDescriptor & MetadataDescriptor>
        extends CollectedClassMemberDescriptors<T> implements MetadataDescriptors<T> {
    
    protected CollectedMetadataDescriptors(final Collection<T> descriptors) {
        super(descriptors);
    }
}
