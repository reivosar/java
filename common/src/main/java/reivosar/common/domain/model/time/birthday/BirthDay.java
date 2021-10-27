package reivosar.common.domain.model.time.birthday;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.domain.model.time.YearMonthDay;

public class BirthDay extends ValueObject<BirthDay>
{
	final YearMonthDay yearMonthDay;

	public BirthDay(YearMonthDay yearMonthDay) {
		this.yearMonthDay = yearMonthDay;
	}

	public boolean isOverAge(Age age) {
		return toAge().isOver(age);
	}

	public Age toAge() {
	    final LocalDate birthday = yearMonthDay.toLocalDate();
	    final LocalDate today    = LocalDate.now();
	    return new Age((int)ChronoUnit.YEARS.between(birthday, today));
	}
}
