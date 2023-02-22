package reivosar.common.util.collection;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * A utility class that provides methods for working with collections.
 */
public final class CollectionUtil {
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private CollectionUtil() {
        // This constructor must be private
    }
    
    /**
     * Checks whether two collections contain the same elements in the same quantities, regardless of order.
     *
     * @param a the first collection
     * @param b the second collection
     * @return true if the collections are equal, false otherwise
     */
    public static boolean isEqualCollection(final Collection<?> a, final Collection<?> b) {
        return CollectionUtils.isEqualCollection(a, b);
    }
}
