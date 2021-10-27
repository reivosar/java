package reivosar.common.domain.model.time;

import java.time.DayOfWeek;
import java.time.LocalDate;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.domain.model.time.holiday.JapaneseHolidays;

public class YearMonthDay extends ValueObject<YearMonthDay>
{
	final LocalDate localDate;

	private YearMonthDay(Integer year, Integer month, Integer day) {
		this(LocalDate.of(year, month, day));
	}

	private YearMonthDay(String year, String month, String day) {
		this(Integer.parseInt(year), Integer.parseInt(month) ,Integer.parseInt(day));
	}

	private YearMonthDay(LocalDate localDate) {
		this.localDate = localDate;
	}

	public static YearMonthDay now() {
		return new YearMonthDay(LocalDate.now());
	}

	public static YearMonthDay of(Integer year, Integer month, Integer day) {
		return new YearMonthDay(year, month, day);
	}

	public static YearMonthDay fromSlashFormat(String yyyymmdd) {
		if (yyyymmdd == null || yyyymmdd.split("/").length != 3) {
			throw new IllegalArgumentException(
				"The format of the argument string must be in yyyy/MM/dd format. parameter:" + yyyymmdd);
		}
		final String[] splitedStr = yyyymmdd.split("/");
		return new YearMonthDay(splitedStr[0], splitedStr[1], splitedStr[2]);
	}

	public DayOfWeek toDayOfWeek() {
		return DayOfWeek.from(toLocalDate());
	}

	public LocalDate toLocalDate() {
		return localDate;
	}

	public boolean equalsYear(int year) {
		return this.localDate.getYear() == year;
	}

	public boolean equalsMonth(int month) {
		return this.localDate.getMonthValue() == month;
	}

	public boolean equalsDay(int day) {
		return this.localDate.getDayOfMonth() == (day);
	}

	public boolean isDayOff() {
		return isWeekend() || isHoliday();
	}

	public boolean isWeekDay() {
		return !isWeekend();
	}

	public boolean isHoliday () {
		return JapaneseHolidays.findHoliday(this).isPresent();
	}

	public boolean isWeekend() {
		final DayOfWeek dayOfWeek = toDayOfWeek();
		return dayOfWeek == DayOfWeek.SUNDAY ||
				dayOfWeek == DayOfWeek.SATURDAY;
	}
}
