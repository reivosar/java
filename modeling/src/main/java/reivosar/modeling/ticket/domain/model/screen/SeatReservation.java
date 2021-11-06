package reivosar.modeling.ticket.domain.model.screen;

import reivosar.common.domain.model.ValueObject;
import reivosar.modeling.ticket.domain.model.schedule.ScheduleId;

public class SeatReservation extends ValueObject<SeatReservation>
{
	final ScheduleId scheduleId;
	final SeatReservationType reservationType;

	public SeatReservation(final ScheduleId scheduleId, final SeatReservationType reservationType) {
		this.scheduleId      = scheduleId;
		this.reservationType = reservationType;
	}

	public boolean reserved() {
		return this.reservationType.reserved();
	}

	public boolean noReservation() {
		return !reserved();
	}

}
