package reivosar.modeling.ticket.domain.model.price;

import reivosar.common.domain.model.time.HourMinute;
import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.common.domain.model.ValueObject;

public class DateAndTimePriceSpecific extends ValueObject<DateAndTimePriceSpecific>
{
	private final DateAndTimePriceTable priceTable;
	private final DateAndTimePriceType priceType;

	public DateAndTimePriceSpecific(
	    final DateAndTimePriceTable priceTable,
	    final YearMonthDay ymd,
	    final HourMinute hm)
	{
		this.priceTable = priceTable;
		this.priceType  = DateAndTimePriceType.of(ymd, hm);
	}

	public Price calcPrice() {
		switch (priceType) {
		case WEEKDAY_NORMAL_PRICE:
			return priceTable.weekdayNormalPrice();
		case WEEKDAY_LATE_PRICE:
			return priceTable.weekdayLatePrice();
		case WEEKDAY_MOVIEDAY_PRICE:
			return priceTable.weekdayMovieDayPrice();
		case HOLIDAY_NORMAL_PRICE:
			return priceTable.holidayNormalPrice();
		case HOLIDAY_LATE_PRICE:
			return priceTable.holidayLatePrice();
		case HOLIDAY_MOVIEDAY_PRICE:
			return priceTable.holidayMovieDayPrice();
		}
		throw new IllegalStateException();
	}
}
