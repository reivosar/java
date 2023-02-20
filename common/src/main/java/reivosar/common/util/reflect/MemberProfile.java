package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.reflect.Member;

public class MemberProfile extends Model {
    
    private final Member member;
    private final ClassProfile classProfile;
    
    MemberProfile(final Member member) {
        this.member = member;
        this.classProfile = new ClassProfile(member.getDeclaringClass());
    }
    
    boolean equalsByName(final String name) {
        return getName().equals(name);
    }
    
    String getName() {
        return member.getName();
    }
}
