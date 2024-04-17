package reivosar.common.lang.reflect.member;

/**
 * An interface for accessing and instantiating constructors at runtime.
 */
public interface ConstructorAccessor {
    
    /**
     * Creates a new instance of the type represented by this constructor, using the specified parameters.
     *
     * @param parameters the parameters to pass to the constructor
     * @param <T>        the type of the object created by the constructor
     * @return a new instance of the type represented by this constructor
     */
    <T> T newInstance(final Object... parameters);
}