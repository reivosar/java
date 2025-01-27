package reivosar.common.domain.model;

import java.io.Serializable;

/**
 * Represents a unique identifier for an aggregate.
 */
public interface AggregateId extends Serializable {

    /**
     * Returns the string representation of the aggregate identifier.
     *
     * @return a unique identifier as a {@code String}.
     */
    String asString();

    /**
     * Returns the type of the aggregate associated with this identifier.
     *
     * @return the aggregate type.
     */
    Class<?> getAggregateType();
}
