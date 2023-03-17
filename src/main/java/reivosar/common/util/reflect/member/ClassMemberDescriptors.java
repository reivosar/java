package reivosar.common.util.reflect.member;

import java.util.Collection;
import java.util.Optional;

/**
 * A generic interface for a collection of ClassMemberDescriptor objects.
 *
 * @param <T> the type of ClassMemberDescriptor contained in this collection
 */
public interface ClassMemberDescriptors<T extends ClassMemberDescriptor> {
    
    /**
     * Returns a collection of member names contained in this collection.
     *
     * @return a collection of member names
     */
    Collection<String> getNames();
    
    /**
     * Returns the number of members contained in this collection.
     *
     * @return the number of members
     */
    int getMemberCount();
    
    /**
     * Returns a collection of member names for which descriptors are available.
     *
     * @return a collection of described member names
     */
    Collection<String> getDescribedMembers();
    
    /**
     * Returns a collection of ClassMemberDescriptor objects contained in this collection.
     *
     * @return a collection of ClassMemberDescriptor objects
     */
    Collection<T> getDescriptors();
    
    /**
     * Returns an optional describing the first element of this collection, or an empty optional
     * if this collection is empty.
     *
     * @return an optional describing the first element of this collection, or an empty optional
     * if this collection is empty
     */
    default Optional<T> findFirst() {
        return getDescriptors().stream().findFirst();
    }
    
    /**
     * Returns the first element of this collection, or throws a {@code NullPointerException}
     * if this collection is empty.
     *
     * @return the first element of this collection
     * @throws NullPointerException if this collection is empty
     */
    default T firstValue() throws NullPointerException {
        return findFirst().orElseThrow(NullPointerException::new);
    }
    
    /**
     * Returns the first element of this collection, or {@code null} if this collection is empty.
     *
     * @return the first element of this collection, or {@code null} if this collection is empty
     */
    default T nullableFirstValue() {
        return findFirst().orElse(null);
    }
}