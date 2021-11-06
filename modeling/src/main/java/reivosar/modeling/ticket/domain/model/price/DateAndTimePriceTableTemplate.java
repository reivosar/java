package reivosar.modeling.ticket.domain.model.price;

import java.util.Currency;
import java.util.Locale;

import reivosar.common.domain.model.money.Money;

public abstract class DateAndTimePriceTableTemplate implements DateAndTimePriceTable
{
	@Override
	public final Price weekdayNormalPrice() {
		return new Price(getMoeny(weekdayNormalPrimitivePrice()));
	}

	protected abstract int weekdayNormalPrimitivePrice();

	@Override
	public final Price weekdayLatePrice() {
		return new Price(getMoeny(weekdayLatePrimitivePrice()));
	}

	protected abstract int weekdayLatePrimitivePrice();

	@Override
	public final Price holidayNormalPrice() {
		return new Price(getMoeny(holidayNormalPrimitivePrice()));
	}

	protected abstract int holidayNormalPrimitivePrice();

	@Override
	public final Price holidayLatePrice() {
		return new Price(getMoeny(holidayLatePrimitivePrice()));
	}

	protected abstract int holidayLatePrimitivePrice();

	@Override
	public final Price weekdayMovieDayPrice() {
		return new Price(getMoeny(weekdayMovieDayPrimitivePrice()));
	}

	protected abstract int weekdayMovieDayPrimitivePrice();

	@Override
	public final Price holidayMovieDayPrice() {
		return new Price(getMoeny(holidayMovieDayPrimitivePrice()));
	}

	protected int holidayMovieDayPrimitivePrice() {
		// シネマシティズンのみ平日と休日で料金が異なる
		return weekdayMovieDayPrimitivePrice();
	}

	private Money getMoeny(int value) {
		return new Money(value, getCurrency());
	}

	protected Currency getCurrency() {
		return Currency.getInstance(Locale.JAPAN);
	}
}
