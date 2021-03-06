package ticketmodeling.domain.model.screen;

import reivosar.common.util.model.Entity;
import ticketmodeling.domain.model.schedule.ScheduleId;

public class Seat extends Entity<SeatId, Seat>
{
	final SeatId seatId;
	final SeatProfile profile;
	final SeatReservation reservation;
	final ScreenId screenId;

	public Seat(
		final SeatId seatId,
		final SeatProfile profile,
		final SeatReservation reservation,
		final ScreenId screenId)
	{
		this.seatId      = seatId;
		this.profile     = profile;
		this.reservation = reservation;
		this.screenId    = screenId;
	}

	public boolean reservedSeat() {
		return this.reservation.reserved();
	}

	public boolean emptySeat() {
		return this.reservation.noReservation();
	}

	public ScheduleId scheduleId () {
		return this.reservation.scheduleId;
	}

	@Override
	public SeatId publicId() {
		return seatId;
	}
}
