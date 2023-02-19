package reivosar.common.util.reflect;

/**
 * The ClassObjectProfile interface provides methods to obtain information about a class object.
 */
public interface ClassProfile {
    
    /**
     * Returns the class object that declares the class represented by this object.
     *
     * @return the declaring class object
     */
    Class<?> getDeclaringClass();
    
    /**
     * Returns the name of the package of the class represented by this object.
     *
     * @return the package name
     */
    String getPackageName();
    
    /**
     * Returns the name of the class represented by this object.
     *
     * @return the class name
     */
    String getClassName();
}