package reivosar.common.util.reflect.member;

/**
 * An interface for accessing and invoking methods.
 */
public interface MethodAccessor {
    
    /**
     * Invokes a non-static method on the specified target object with the specified parameters.
     *
     * @param target     the target object on which to invoke the method
     * @param parameters the parameters to pass to the method
     * @return the result of invoking the method
     */
    Object invokeMethod(final Object target, final Object... parameters);
    
    /**
     * Invokes a static method with the specified parameters.
     *
     * @param parameters the parameters to pass to the method
     * @return the result of invoking the method
     */
    Object invokeStaticMethod(final Object... parameters);
}