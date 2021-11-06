package reivosar.modeling.ticket.domain.model.screen;

import java.util.Collection;

import reivosar.common.domain.model.Entity;
import reivosar.modeling.ticket.domain.model.schedule.ScheduleId;
import reivosar.modeling.ticket.domain.model.theater.TheaterId;

public class Screen extends Entity<ScreenId, Screen>
{
	final ScreenId screenId;
	final ScreenProfile screenProfile;
	final Seats seats;
	final TheaterId theaterId;

	public Screen(
	    final ScreenId screenId,
	    final ScreenProfile screenProfile,
	    final Seats seats,
	    final TheaterId theaterId)
	{
		this.screenId      = screenId;
		this.screenProfile = screenProfile;
		this.seats         = seats;
		this.theaterId     = theaterId;
	}

	public Collection<ScheduleId> allSchedules() {
		return this.seats.allSchedules();
	}

	public boolean reservationPossible(final ScheduleId scheduleId) {
		return this.seats.reservationPossible(scheduleId);
	}

	public Collection<Seat> allScheduledSeats(final ScheduleId scheduleId) {
		return this.seats.allScheduledSeats(scheduleId);
	}

	public Collection<Seat> reserveScheduledSeats(final ScheduleId scheduleId) {
		return this.seats.reserveScheduledSeats(scheduleId);
	}

	public Collection<Seat> emptyScheduledSeats(final ScheduleId scheduleId) {
		return this.seats.emptyScheduledSeats(scheduleId);
	}

	@Override
	public ScreenId identity() {
		return screenId;
	}
}
