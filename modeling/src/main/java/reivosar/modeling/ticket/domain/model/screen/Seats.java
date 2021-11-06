package reivosar.modeling.ticket.domain.model.screen;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import reivosar.common.domain.model.ValueObject;
import reivosar.modeling.ticket.domain.model.schedule.ScheduleId;

public class Seats extends ValueObject<Seat>
{
	final Collection<Seat> values;

	public Seats(Collection<Seat> values) {
		this.values = values;
	}

	public boolean reservationPossible(final ScheduleId scheduleId) {
		return !emptyScheduledSeats(scheduleId).isEmpty();
	}

	public Collection<Seat> allScheduledSeats(final ScheduleId scheduleId) {
		return this.values.stream()
			.filter(seat -> seat.scheduleId().equals(scheduleId))
			.collect(Collectors.toUnmodifiableList());
	}

	public Collection<Seat> reserveScheduledSeats(final ScheduleId scheduleId) {
		return this.allScheduledSeats(scheduleId).stream()
			.filter(Seat::reservedSeat)
			.collect(Collectors.toUnmodifiableList());
	}

	public Collection<Seat> emptyScheduledSeats(final ScheduleId scheduleId) {
		return this.allScheduledSeats(scheduleId).stream()
			.filter(Seat::emptySeat)
			.collect(Collectors.toUnmodifiableList());
	}

	public Collection<ScheduleId> allSchedules() {
		return values.stream()
			.flatMap(seat -> Stream.of(seat.scheduleId()))
			.collect(Collectors.toUnmodifiableSet());
	}
}
