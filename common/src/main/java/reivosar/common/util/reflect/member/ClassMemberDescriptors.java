package reivosar.common.util.reflect.member;

import java.util.Collection;

/**
 * This interface represents a collection of class member descriptors, which include methods and fields.
 *
 * @param <T> the type of the class member descriptor.
 * @param <S> the type of the class member descriptors.
 */
public interface ClassMemberDescriptors<T extends ClassMemberDescriptor, S extends ClassMemberDescriptors<T, S>> {
    
    /**
     * Filters the class member descriptors by name.
     *
     * @param name the name to filter by.
     * @return a new collection of class member descriptors filtered by name.
     */
    S filterByName(String name);
    
    /**
     * Filters the class member descriptors by a described member.
     *
     * @param describedMember the described member to filter by.
     * @return a new collection of class member descriptors filtered by the described member.
     */
    S filterByDescribedMember(String describedMember);
    
    /**
     * Gets a collection of method descriptors from the class member descriptors.
     *
     * @return a collection of method descriptors.
     */
    Collection<T> getDescriptors();
}