package reivosar.common.lang.reflect.member;

import reivosar.common.lang.reflect.ClassModifier;

/**
 * A descriptor for a class member, such as a field, method, or constructor.
 */
public interface ClassMemberDescriptor {
    
    /**
     * Returns the modifier for this class member, represented as a `ClassModifier` enumeration value.
     *
     * @return the modifier for this class member
     */
    ClassModifier getClassModifier();
    
    /**
     * Returns the name of this class member.
     *
     * @return the name of this class member
     */
    String getName();
    
    /**
     * Returns described string of this class member.
     *
     * @return the described string of this class member
     */
    String getDescribedMember();
}