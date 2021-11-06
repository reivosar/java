package reivosar.modeling.ticket.domain.model.screen;

import reivosar.common.domain.model.ValueObject;

public class SeatCapacity extends ValueObject<SeatCapacity>
{
	final int value;

	public SeatCapacity(final int value) {
		this.value = value;
	}
}
