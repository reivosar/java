package reivosar.common.util.reflect;

import java.util.Collection;
import reivosar.common.util.reflect.member.ClassMemberDescriptor;

class ClassMemberDescriptors<T extends ClassMemberDescriptor> {
    
    private final ClassMemberCollectionAccessor<T> classMemberCollectionAccessor;
    
    protected ClassMemberDescriptors(final Collection<T> tCollection) {
        this.classMemberCollectionAccessor = new ClassMemberCollectionAccessor<>(tCollection);
    }
    
    Collection<T> filter(final String name) {
        return classMemberCollectionAccessor.filter(name);
    }
    
    int getMemberCount() {
        return classMemberCollectionAccessor.getMemberCount();
    }
    
    Collection<T> getDescriptors() {
        return classMemberCollectionAccessor.getDescriptors();
    }
    
    Collection<String> getMemberNames() {
        return classMemberCollectionAccessor.getMemberNames();
    }
    
    Collection<String> getDescribedMembers() {
        return classMemberCollectionAccessor.getDescribedMembers();
    }
}
