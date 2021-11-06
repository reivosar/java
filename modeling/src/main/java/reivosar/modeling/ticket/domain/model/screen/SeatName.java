package reivosar.modeling.ticket.domain.model.screen;

import reivosar.common.domain.model.ValueObject;

public class SeatName extends ValueObject<SeatName>
{
	final String value;

	public SeatName(final String value) {
		this.value = value;
	}
}
