package reivosar.common.util.collection;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

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
    
    /**
     * Compares two collections to see if they contain the same elements in the same order.
     *
     * @param a the first collection to compare
     * @param b the second collection to compare
     * @return true if the collections contain the same elements in the same order, false otherwise
     */
    public static boolean isSameOrderCollection(final Collection<?> a, final Collection<?> b) {
        if (a == null || b == null) {
            return a == b;
        }
        if (a.size() != b.size()) {
            return false;
        }
        Iterator<?> iteratorA = a.iterator();
        Iterator<?> iteratorB = b.iterator();
        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            Object nextA = iteratorA.next();
            Object nextB = iteratorB.next();
            if (nextA == null) {
                if (nextB != null) {
                    return false;
                }
            } else {
                if (nextB == null || !nextA.getClass().equals(nextB.getClass()) || !nextA.equals(nextB)) {
                    return false;
                }
            }
        }
        return true;
    }
}
