package reivosar.common.domain.model;

import reivosar.common.data.model.Model;

/**
 * The Entity abstract class represents an entity in a domain model. An entity has an identity,
 * <p>
 * which is an object that uniquely identifies it, and it can have additional attributes and behavior
 * <p>
 * that are specific to its domain.
 *
 * @param <ID> the type of the identity object
 * @param <E>  the type of the subclass extending Entity
 */
public abstract class Entity<ID extends Identity<ID>, E>
        extends Model {
    
    /**
     * Returns the identity of this entity.
     *
     * @return the identity of this entity
     */
    public abstract ID identity();
}