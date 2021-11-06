package reivosar.modeling.ticket.domain.model.audience;

import reivosar.common.domain.model.Entity;
import reivosar.common.domain.model.money.Money;
import reivosar.common.domain.model.time.HourMinute;
import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.modeling.ticket.domain.model.price.DateAndTimePriceSpecific;
import reivosar.modeling.ticket.domain.model.price.Price;
import reivosar.modeling.ticket.domain.model.schedule.Schedule;
import reivosar.modeling.ticket.domain.model.schedule.ScheduledTime;
import reivosar.modeling.ticket.domain.model.ticket.Ticket;

public abstract class Audience<ENTITY extends Audience<ENTITY>>
	extends Entity<AudienceId, ENTITY>
{
	final AudienceId id;

	protected Audience(AudienceId id) {
		this.id = id;
	}

	public final Price calcScreenPrice(final YearMonthDay ymd, final HourMinute hm) {
		DateAndTimePriceSpecific specific = new DateAndTimePriceSpecific(
				type().priceTable(), ymd, hm);
		return specific.calcPrice();
	}

	public final Price calcScreenPrice(final ScheduledTime scheduledTime) {
		return calcScreenPrice(scheduledTime.yearMonthDay(), scheduledTime.hourMinute());
	}

	public final Ticket selectTicketToBuy(final Schedule schedule) {
		return new Ticket(id, schedule.identity(), calcScreenPrice(schedule.time()));
	}

	public final Ticket buyTicket(final Schedule schedule, final Money money) {
		return selectTicketToBuy(schedule).buyTicket(money);
	}

	@Override
	public AudienceId identity() {
		return id;
	}

	protected abstract AudienceType type();
}
