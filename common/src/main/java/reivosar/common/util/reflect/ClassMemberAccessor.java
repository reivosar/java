package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;

import java.lang.reflect.Member;

class ClassMemberAccessor {
    
    private final String name;
    private final AccessScope accessScope;
    
    ClassMemberAccessor(final Member member) {
        this.name = member.getName();
        this.accessScope = AccessScope.of(new ClassModifier(member.getModifiers()));
    }
    
    String getName() {
        return name;
    }
    
    boolean equalsByName(final String name) {
        return this.getName().equals(ObjectUtil.requireNonNull("name", name));
    }
    
    AccessScope getAccessScope() {
        return accessScope;
    }
    
    public boolean equalsByAccessScope(final AccessScope accessScope) {
        return this.accessScope == accessScope;
    }
}
