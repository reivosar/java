package reivosar.common.domain.model.time.holiday;

import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.common.util.lang.ObjectUtil;

import java.util.Collection;
import java.util.Optional;

/**
 * The {@code Holidays} interface represents a collection of holidays.
 * Implementations of this interface provide methods to retrieve holidays
 * and check if a specific date is a holiday.
 */
public interface Holidays {

    /**
     * Returns a collection of holidays.
     *
     * @return the collection of holidays
     */
    Collection<Holiday> getHolidays();

    /**
     * Finds a holiday for the specified {@code YearMonthDay}.
     *
     * @param yearMonthDay the year-month-day representing the date to find a holiday for
     * @return an {@code Optional} containing the holiday for the specified date, or empty if not found
     * @throws NullPointerException if the specified {@code yearMonthDay} is {@code null}
     */
    default Optional<Holiday> findHoliday(final YearMonthDay yearMonthDay) {
        ObjectUtil.requireNonNull("yearMonthDay", yearMonthDay);
        return getHolidays().stream()
                .filter(holiday -> holiday.yearMonthDay.equals(yearMonthDay))
                .findAny();
    }

    /**
     * Checks if the specified {@code YearMonthDay} represents a holiday.
     *
     * @param yearMonthDay the year-month-day representing the date to check
     * @return {@code true} if the specified date is a holiday, otherwise {@code false}
     */
    default boolean isHoliday(final YearMonthDay yearMonthDay) {
        return findHoliday(yearMonthDay).isPresent();
    }
}