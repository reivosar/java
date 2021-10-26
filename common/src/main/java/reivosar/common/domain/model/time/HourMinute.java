package reivosar.common.domain.model.time;

import java.time.LocalTime;

import reivosar.common.util.model.ValueObject;

public class HourMinute extends ValueObject<HourMinute>
{
	final LocalTime localTime;

	public HourMinute(int hour, int minute) {
		this.localTime = LocalTime.of(hour, minute);
	}

	public LocalTime toLocalTime() {
		return localTime;
	}

	public boolean equalsHour(int hour) {
		return this.localTime.getHour() == hour;
	}

	public boolean equalsMinute(int minute) {
		return this.localTime.getMinute() == minute;
	}

	public boolean isBefore(HourMinute hm) {
		return toLocalTime().isBefore(hm.toLocalTime());
	}

	public boolean isBeforeThan(HourMinute hm) {
		return toLocalTime().compareTo(hm.toLocalTime()) <= 0;
	}

	public boolean isAfter(HourMinute hm) {
		return toLocalTime().isAfter(hm.toLocalTime());
	}

	public boolean isAfterThan(HourMinute hm) {
		return toLocalTime().compareTo(hm.toLocalTime()) >= 0;
	}
}
