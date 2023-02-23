package reivosar.common.util.reflect;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.lang.reflect.Member;

class ClassMemberAccessor extends Model {
    
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
