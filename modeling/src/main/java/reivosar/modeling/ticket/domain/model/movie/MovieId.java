package reivosar.modeling.ticket.domain.model.movie;

import reivosar.common.domain.model.Identity;

public class MovieId extends Identity<MovieId>
{
	final String value;

	public MovieId(final String value) {
		this.value = value;
	}
}
