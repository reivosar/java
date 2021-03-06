package ticketmodeling.domain.model.movie;

import reivosar.common.util.model.ValueObject;

public class MovieProfile extends ValueObject<MovieProfile>
{
	final MovieName name;

	public MovieProfile(final MovieName name) {
		this.name = name;
	}
}
