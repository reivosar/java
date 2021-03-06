package ticketmodeling.domain.model.ticket;

import reivosar.common.domain.model.money.Money;
import reivosar.common.util.model.Entity;
import ticketmodeling.domain.model.audience.AudienceId;
import ticketmodeling.domain.model.price.Price;
import ticketmodeling.domain.model.schedule.ScheduleId;

public class Ticket extends Entity<TicketId, Ticket>
{
	final TicketId ticketId;
	final AudienceId audienceId;
	final ScheduleId scheduleId;
	final Price price;

	public Ticket(
		final AudienceId audienceId,
		final ScheduleId scheduleId,
		final Price price)
	{
		this.ticketId   = new TicketId();
		this.audienceId = audienceId;
		this.scheduleId = scheduleId;
		this.price      = price;
	}

	@Override
	public TicketId publicId() {
		return ticketId;
	}

	public Ticket buyTicket(final Money money) {
		// イベント送信を行う
		return this;
	}
}
