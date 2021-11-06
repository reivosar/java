package reivosar.modeling.ticket.domain.model.movie;

import reivosar.common.domain.model.ValueObject;

public class MovieProfile extends ValueObject<MovieProfile>
{
	final MovieName name;

	public MovieProfile(final MovieName name) {
		this.name = name;
	}
}
