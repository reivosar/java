package reivosar.common.domain.model;

import reivosar.common.util.model.Model;

/**
 * The classes that inherit from this object 
 * hold things and business properties as 
 * attributes and have the required behavior.
 * 
 * It also holds a unique identifier, which 
 * identifies it with other objects.
 * 
 * @param <ID>
 * @param <E>
 */
public abstract class Entity<ID extends Identity<ID>, E>
	extends Model {

	/**
	 * Returns a unique identifier for this object.
	 * 
	 * @return  a unique identifier for this object
	 */
	public abstract ID identity();
}
