package reivosar.common.lang.function;

/**
 * Represents an operation that accepts no input arguments and returns no result.
 * <p>
 * It is similar to the standard {@link java.util.function.Consumer} interface, but with no arguments.
 */
@FunctionalInterface
public interface VoidConsumer {
    
    /**
     * Performs this operation on a given input.
     */
    void accept();
}