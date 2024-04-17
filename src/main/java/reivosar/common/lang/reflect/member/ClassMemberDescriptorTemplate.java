package reivosar.common.lang.reflect.member;

import reivosar.common.data.model.Model;
import reivosar.common.lang.reflect.ClassModifier;

import java.lang.reflect.Member;

class ClassMemberDescriptorTemplate<T extends Member> extends Model implements ClassMemberDescriptor {
    
    private final T object;
    
    protected ClassMemberDescriptorTemplate(final T object) {
        this.object = object;
    }
    
    @Override
    public ClassModifier getClassModifier() {
        return new ClassModifier(this.object.getModifiers());
    }
    
    @Override
    public String getName() {
        return this.object.getName();
    }
    
    @Override
    public String getDescribedMember() {
        return this.object.toString();
    }
}
