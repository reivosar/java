package reivosar.common.util.model;

import java.util.Optional;

/**
 * The Result interface represents the result of a computation that may either succeed or fail.
 *
 * @param <T> the type of the result value
 */
public interface Result<T> {
    
    /**
     * Checks whether the computation succeeded.
     *
     * @return true if the computation succeeded, false otherwise
     */
    boolean success();
    
    /**
     * Checks whether the computation failed.
     *
     * @return true if the computation failed, false otherwise
     */
    boolean fail();
    
    /**
     * Returns the result value if the computation succeeded.
     *
     * @return an optional containing the result value, or an empty optional if the computation failed
     */
    Optional<T> result();
    
    /**
     * Returns the error that caused the computation to fail, if the computation failed.
     *
     * @return an optional containing the error, or an empty optional if the computation succeeded
     */
    Optional<Throwable> error();
}