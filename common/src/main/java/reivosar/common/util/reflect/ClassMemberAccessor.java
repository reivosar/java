package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.lang.reflect.Member;

class ClassMemberAccessor extends Model {
    
    private final String name;
    private final String describedMember;
    private final AccessScope accessScope;
    
    ClassMemberAccessor(final Member member) {
        this.name = member.getName();
        this.describedMember = member.toString();
        this.accessScope = AccessScope.of(new ClassModifier(member.getModifiers()));
    }
    
    String getName() {
        return name;
    }
    
    boolean equalsByName(final String name) {
        return this.getName().equals(ObjectUtil.requireNonNull("name", name));
    }
    
    String getDescribedMember() {
        return describedMember;
    }
    
    boolean equalsByDescribedMember(final String describedMember) {
        return this.getDescribedMember().equals(ObjectUtil.requireNonNull("describedMember", describedMember));
    }
    
    AccessScope getAccessScope() {
        return accessScope;
    }
    
    public boolean equalsByAccessScope(final AccessScope accessScope) {
        return this.accessScope == ObjectUtil.requireNonNull("accessScope", accessScope);
    }
}
