package reivosar.common.domain.model;

import java.io.Serializable;

/**
 * Represents a type of aggregate.
 */
public interface AggregateType extends Serializable {

    /**
     * Returns the string representation of the aggregate type.
     *
     * @return the aggregate type as a {@code String}.
     */
    String asString();

}
