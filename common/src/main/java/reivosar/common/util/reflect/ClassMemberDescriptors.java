package reivosar.common.util.reflect;

import java.util.Collection;

class ClassMemberDescriptors<T extends ClassMemberDescriptor> {
    
    private final ClassMemberCollectionAccessor<T> classMemberCollectionAccessor;
    
    protected ClassMemberDescriptors(final Collection<T> tCollection) {
        this.classMemberCollectionAccessor = new ClassMemberCollectionAccessor<>(tCollection);
    }
    
    Collection<T> filterByName(final String name) {
        return classMemberCollectionAccessor.filterByName(name);
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
}
