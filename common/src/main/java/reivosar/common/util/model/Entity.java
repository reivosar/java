package reivosar.common.util.model;

public abstract class Entity<ID extends Identity<ID>, E>
	extends Model {

	public abstract ID publicId();
}
