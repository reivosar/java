package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.reflect.Member;

class MemberAccessor extends Model {
    
    private final AccessScope accessScope;
    private final boolean staticAccess;
    
    MemberAccessor(final Member member) {
        final ClassModifier classModifier = getModifier(member);
        this.accessScope = AccessScope.of(classModifier);
        this.staticAccess = classModifier.isStatic();
    }
    
    private ClassModifier getModifier(final Member member) {
        return new ClassModifier((member != null) ? member.getModifiers() : -1);
    }
    
    AccessScope getAccessScope() {
        return accessScope;
    }
    
    boolean isStaticAccess() {
        return staticAccess;
    }
}
