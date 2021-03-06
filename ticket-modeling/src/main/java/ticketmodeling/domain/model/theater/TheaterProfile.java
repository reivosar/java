package ticketmodeling.domain.model.theater;

import reivosar.common.util.model.ValueObject;

public class TheaterProfile extends ValueObject<TheaterProfile>
{
	final TheaterName name;

	public TheaterProfile(final TheaterName name) {
		this.name = name;
	}
}
