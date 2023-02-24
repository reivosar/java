package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.reflect.Member;

abstract class ClassMemberDescriptor extends Model {
    
    private final ClassMemberAccessor classMemberAccessor;
    
    protected ClassMemberDescriptor(final Member member) {
        this.classMemberAccessor = new ClassMemberAccessor(member);
    }
    
    public String getName() {
        return classMemberAccessor.getName();
    }
    
    public boolean equalsByName(final String name) {
        return classMemberAccessor.equalsByName(name);
    }
    
    public String getDescribedMember() {
        return classMemberAccessor.getDescribedMember();
    }
    
    public boolean equalsByDescribedMember(final String describedMember) {
        return classMemberAccessor.equalsByDescribedMember(describedMember);
    }
    
    public AccessScope getAccessScope() {
        return classMemberAccessor.getAccessScope();
    }
    
    public boolean equalsByAccessScope(final AccessScope accessScope) {
        return classMemberAccessor.equalsByAccessScope(accessScope);
    }
}
