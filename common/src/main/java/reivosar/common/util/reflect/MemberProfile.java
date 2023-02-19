package reivosar.common.util.reflect;

import reivosar.common.util.model.Model;

import java.lang.reflect.Member;

public class MemberProfile extends Model {
    
    private final Member member;
    
    MemberProfile(final Member member) {
        this.member = member;
    }
    
    boolean equalsByName(final String name) {
        return getName().equals(name);
    }
    
    String getName() {
        return member.getName();
    }
}
