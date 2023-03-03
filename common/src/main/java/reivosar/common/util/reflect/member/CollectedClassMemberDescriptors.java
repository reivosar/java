package reivosar.common.util.reflect.member;

import reivosar.common.util.model.Model;

import java.util.Collection;

abstract class CollectedClassMemberDescriptors<T extends ClassMemberDescriptor> extends Model implements ClassMemberDescriptors<T> {
    
    private final Collection<T> descriptors;
    
    protected CollectedClassMemberDescriptors(final Collection<T> descriptors) {
        this.descriptors = descriptors;
    }
    
    @Override
    public Collection<String> getNames() {
        return getDescriptors().stream().map(ClassMemberDescriptor::getName).toList();
    }
    
    @Override
    public int getMemberCount() {
        return getDescriptors().size();
    }
    
    @Override
    public Collection<String> getDescribedMembers() {
        return getDescriptors().stream().map(ClassMemberDescriptor::getDescribedMember).toList();
    }
    
    @Override
    public Collection<T> getDescriptors() {
        return this.descriptors;
    }
}
