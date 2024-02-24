package reivosar.common.util.lang;

import java.lang.reflect.Method;

/**
 * A utility class for invoking methods using reflection.
 */
public final class MethodUtil {
    
    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private MethodUtil() {
        // This constructor must be private
    }
    
    /**
     * Invokes the specified method on the specified target object with the specified parameters.
     *
     * @param method     the method to be invoked
     * @param target     the object on which to invoke the method
     * @param parameters the parameters to pass to the method
     * @return the result of the method invocation
     * @throws IllegalStateException if an exception is thrown during the method invocation
     */
    public static Object invokeMethod(final Method method, final Object target, final Object... parameters) {
        ObjectUtil.requireNonNull("method", method);
        ObjectUtil.requireNonNull("target", target);
        try {
            return method.invoke(target, ArrayUtil.nullToEmpty(parameters));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    /**
     * Invokes the specified static method with the specified parameters.
     *
     * @param method     the static method to be invoked
     * @param parameters the parameters to pass to the method
     * @return the result of the method invocation
     * @throws IllegalStateException if an exception is thrown during the method invocation
     */
    public static Object invokeStaticMethod(final Method method, final Object... parameters) {
        ObjectUtil.requireNonNull("method", method);
        try {
            return method.invoke(null, ArrayUtil.nullToEmpty(parameters));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
}