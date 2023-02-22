package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.reflect.Member;

abstract class ClassMemberDescriptor extends Model {
    
    private final ClassMemberAccessor classMemberAccessor;
    
    protected ClassMemberDescriptor(final Member member) {
        this.classMemberAccessor = new ClassMemberAccessor(member);
    }
    
    String getName() {
        return classMemberAccessor.getName();
    }
    
    boolean equalsByName(final String name) {
        return classMemberAccessor.equalsByName(name);
    }
    
    AccessScope getAccessScope() {
        return classMemberAccessor.getAccessScope();
    }
    
    boolean equalsByAccessScope(final AccessScope accessScope) {
        return classMemberAccessor.equalsByAccessScope(accessScope);
    }
}
