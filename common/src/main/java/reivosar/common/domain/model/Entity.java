package reivosar.common.domain.model;

import reivosar.common.util.model.Model;

public abstract class Entity<ID extends Identity<ID>, E>
	extends Model {

	public abstract ID publicId();
}
