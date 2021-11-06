package reivosar.modeling.ticket.domain.model.schedule;

import reivosar.common.domain.model.time.HourMinute;
import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.common.domain.model.ValueObject;

public class ScheduledTime extends ValueObject<ScheduledTime>
{
	final YearMonthDay yearMonthDay;
	final HourMinute hourMinute;

	public ScheduledTime(final YearMonthDay yearMonthDay, final HourMinute hourMinute) {
		this.yearMonthDay = yearMonthDay;
		this.hourMinute   = hourMinute;
	}

	public ScheduledTime(final HourMinute hourMinute) {
		this.yearMonthDay = YearMonthDay.now();
		this.hourMinute   = hourMinute;
	}

	public YearMonthDay yearMonthDay() {
		return yearMonthDay;
	}

	public HourMinute hourMinute() {
		return hourMinute;
	}
}
