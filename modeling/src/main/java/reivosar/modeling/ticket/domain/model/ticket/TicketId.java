package reivosar.modeling.ticket.domain.model.ticket;

import reivosar.common.domain.model.Identity;

public class TicketId extends Identity<TicketId>
{
	final String value;

	public TicketId() {
		this.value = genereteNativeIdByUUID();
	}
}
