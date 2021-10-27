package reivosar.common.domain.model.time.holiday;

import reivosar.common.domain.model.ValueObject;

public class HolidayName extends ValueObject<HolidayName>
{
	final String value;

	public HolidayName(String value) {
		this.value = value;
	}
}
