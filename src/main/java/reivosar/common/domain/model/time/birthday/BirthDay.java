package reivosar.common.domain.model.time.birthday;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.common.util.lang.ObjectUtil;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Represents a birth date as a value object.
 */
public class BirthDay extends ValueObject<BirthDay> {
    final YearMonthDay yearMonthDay;
    
    /**
     * Creates a new instance of BirthDay with the specified YearMonthDay.
     *
     * @param yearMonthDay the YearMonthDay representing the birth date.
     */
    public BirthDay(final YearMonthDay yearMonthDay) {
        ObjectUtil.requireNonNull("YearMonthDay", yearMonthDay);
        this.yearMonthDay = yearMonthDay;
    }
    
    /**
     * Checks if the age of the person represented by this BirthDay is over the specified age.
     *
     * @param age the age to check against.
     * @return true if the age of the person is over the specified age, false otherwise.
     */
    public boolean isOverAge(final Age age) {
        ObjectUtil.requireNonNull("Age", age);
        return toAge().isOver(age);
    }
    
    /**
     * Calculates the Age of the person represented by this BirthDay.
     *
     * @return the Age object representing the age of the person.
     */
    public Age toAge() {
        final LocalDate birthday = yearMonthDay.toLocalDate();
        final LocalDate today = LocalDate.now();
        return new Age((int) ChronoUnit.YEARS.between(birthday, today));
    }
}