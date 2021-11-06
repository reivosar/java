package reivosar.modeling.ticket.domain.model.movie;

import reivosar.common.domain.model.Entity;

public class Movie extends Entity<MovieId, Movie>
{
	final MovieId id;
	final MovieProfile profile;

	public Movie(
		final MovieId id,
		final MovieProfile profile)
	{
		this.id       = id;
		this.profile  = profile;
	}

	@Override
	public MovieId identity() {
		return id;
	}
}
