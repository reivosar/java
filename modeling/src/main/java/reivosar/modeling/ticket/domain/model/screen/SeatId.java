package reivosar.modeling.ticket.domain.model.screen;

import reivosar.common.domain.model.Identity;

public class SeatId extends Identity<SeatId>
{
	final String value;

	public SeatId(final String value) {
		this.value = value;
	}
}
