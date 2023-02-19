package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

class MemberAccessor extends Model {
    
    private final AccessScope accessScope;
    private final boolean staticAccess;
    
    MemberAccessor(final ClassModifier classModifier) {
        this.accessScope = AccessScope.of(classModifier);
        this.staticAccess = classModifier.isStatic();
    }
    
    AccessScope getAccessScope() {
        return accessScope;
    }
    
    boolean isStaticAccess() {
        return staticAccess;
    }
}
