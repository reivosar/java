package reivosar.common.domain.model;

/**
 * An abstract base class for identity types that represent unique identifiers for domain objects.
 *
 * @param <ID> the type of the identity value
 */
public abstract class Identity<ID> extends ValueObject<ID> implements AggregateId {

}