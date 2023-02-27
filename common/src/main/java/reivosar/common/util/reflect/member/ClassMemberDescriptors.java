package reivosar.common.util.reflect.member;

/**
 * This interface represents a collection of descriptors for class members.
 *
 * @param <T> The type of the implementing class.
 */
public interface ClassMemberDescriptors<T extends ClassMemberDescriptors<T>> {
    
    /**
     * Filters the descriptors by name.
     *
     * @param name The name to filter by.
     * @return A new collection of descriptors containing only the descriptors with the given name.
     */
    T filterByName(String name);
    
    /**
     * Filters the descriptors by the described member.
     *
     * @param describedMember The described member to filter by.
     * @return A new collection of descriptors containing only the descriptors with the given described member.
     */
    T filterByDescribedMember(String describedMember);
}