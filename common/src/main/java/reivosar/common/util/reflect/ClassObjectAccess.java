package reivosar.common.util.reflect;

/**
 * The ClassObjectAccess interface provides methods to access the access scope
 * and staticness of a class object.
 */
public interface ClassObjectAccess {
    
    /**
     * Returns the access scope of the class object.
     *
     * @return the access scope of the class object
     */
    AccessScope getAccessScope();
    
    /**
     * Returns whether the class object is declared static or not.
     *
     * @return true if the class object is declared static, false otherwise
     */
    boolean isStaticAccess();
}