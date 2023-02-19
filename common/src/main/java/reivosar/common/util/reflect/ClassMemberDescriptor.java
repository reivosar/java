package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.annotation.Annotation;
import java.lang.reflect.Member;

abstract class ClassMemberDescriptor extends Model {
    
    private final MemberProfile memberProfile;
    private final MemberAccessor memberAccessor;
    private final MetadataAccessor metadataAccessor;
    
    ClassMemberDescriptor() {
        this.memberProfile = new MemberProfile(getMember());
        this.memberAccessor = new MemberAccessor(getModifier());
        this.metadataAccessor = new MetadataAccessor(getClassAccessibleObject());
    }
    
    protected abstract Member getMember();
    
    boolean equalsByName(final String name) {
        return this.memberProfile.equalsByName(name);
    }
    
    private ClassModifier getModifier() {
        return new ClassModifier(getMember().getModifiers());
    }
    
    AccessScope accessScope() {
        return this.memberAccessor.getAccessScope();
    }
    
    boolean equalsByAccessScope(final AccessScope accessScope) {
        return this.accessScope() == accessScope;
    }
    
    boolean hasAnnotatedMetadata(final Annotation annotation) {
        return this.metadataAccessor.hasAnnotatedMetadata(annotation);
    }
    
    protected abstract ClassAccessibleObject getClassAccessibleObject();
}
