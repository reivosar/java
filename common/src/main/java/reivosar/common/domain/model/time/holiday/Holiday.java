package reivosar.common.domain.model.time.holiday;

import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.common.util.model.ValueObject;

public class Holiday extends ValueObject<Holiday>
{
	final YearMonthDay yearMonthDay;
	final HolidayName holidayName;

	public Holiday(YearMonthDay yearMonthDay, HolidayName holidayName) {
		this.yearMonthDay = yearMonthDay;
		this.holidayName  = holidayName;
	}
}
