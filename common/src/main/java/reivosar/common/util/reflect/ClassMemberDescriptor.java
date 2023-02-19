package reivosar.common.util.reflect;

import java.lang.reflect.Member;

abstract class ClassMemberDescriptor extends ClassDescriptor {
    
    private final Member member;
    
    protected ClassMemberDescriptor(final Member member) {
        this.member = member;
    }
    
    @Override
    public Class<?> getDeclaringClass() {
        return member.getDeclaringClass();
    }
    
    @Override
    protected ClassModifier getClassModifier() {
        return new ClassModifier(this.member.getModifiers());
    }
}
