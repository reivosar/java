package reivosar.common.domain.model;

import java.util.UUID;

/**
 * An abstract base class for identity types that represent unique identifiers for domain objects.
 *
 * @param <ID> the type of the identity value
 */
public abstract class Identity<ID> extends ValueObject<ID> {
    
    /**
     * Generates a new native ID using the UUID algorithm.
     *
     * @return a new ID as a string
     */
    protected static String generateNativeIdByUUID() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Returns an empty identity instance. Useful as a null object representation.
     *
     * @param <ID> the type of the identity value
     * @return an empty identity instance
     */
    @SuppressWarnings("unchecked")
    public static <ID> ID empty() {
        return (ID) new Identity<ID>() {
        };
    }
}