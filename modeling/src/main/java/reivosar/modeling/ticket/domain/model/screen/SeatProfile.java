package reivosar.modeling.ticket.domain.model.screen;

import reivosar.common.domain.model.ValueObject;

public class SeatProfile extends ValueObject<SeatProfile>
{
	final SeatName name;

	public SeatProfile(final SeatName name) {
		this.name = name;
	}
}
