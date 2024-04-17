package reivosar.common.domain.model.time;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.domain.model.time.holiday.LocaleHoliday;
import reivosar.common.lang.ObjectUtil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Locale;

/**
 * A value object that represents a date in year-month-day format.
 * <p>
 * This class provides several methods for manipulating and retrieving information about a date.
 */
public class YearMonthDay extends ValueObject<YearMonthDay> {
    
    final LocalDate localDate;
    
    /**
     * Constructor for creating a YearMonthDay object from individual year, month, and day components.
     *
     * @param year  The year component of the date.
     * @param month The month component of the date.
     * @param day   The day component of the date.
     */
    private YearMonthDay(final Integer year, final Integer month, final Integer day) {
        this(LocalDate.of(
                ObjectUtil.requireNonNull("year", year),
                ObjectUtil.requireNonNull("month", month),
                ObjectUtil.requireNonNull("day", day)
        ));
    }
    
    /**
     * Constructor for creating a YearMonthDay object from a date string in yyyy/MM/dd format.
     *
     * @param year  The year component of the date as a string.
     * @param month The month component of the date as a string.
     * @param day   The day component of the date as a string.
     */
    private YearMonthDay(final String year, final String month, final String day) {
        this(
                Integer.parseInt(ObjectUtil.requireNonNull("year", year)),
                Integer.parseInt(ObjectUtil.requireNonNull("month", month)),
                Integer.parseInt(ObjectUtil.requireNonNull("day", day))
        );
    }
    
    /**
     * Constructor for creating a YearMonthDay object from a LocalDate object.
     *
     * @param localDate The LocalDate object representing the date.
     */
    private YearMonthDay(final LocalDate localDate) {
        ObjectUtil.requireNonNull("localDate", localDate);
        this.localDate = localDate;
    }

    /**
     * Returns a new YearMonthDay object representing the specified year, month, and day.
     *
     * @param year  The year component of the date.
     * @param month The month component of the date.
     * @param day   The day component of the date.
     * @return A new YearMonthDay object representing the specified date.
     */
    public static YearMonthDay of(final Integer year, final Integer month, final Integer day) {
        ObjectUtil.requireNonNull("year", year);
        ObjectUtil.requireNonNull("month", month);
        ObjectUtil.requireNonNull("day", day);
        return new YearMonthDay(year, month, day);
    }
    
    /**
     * Returns a new YearMonthDay object parsed from a date string in yyyy/MM/dd format.
     *
     * @param format The date string in yyyy/MM/dd format.
     * @return A new YearMonthDay object representing the specified date.
     * @throws IllegalArgumentException if the input string is not in the expected format.
     */
    public static YearMonthDay fromSlashFormat(final String format) {
        if (format == null || format.split("/").length != 3) {
            throw new IllegalArgumentException(
                    "The format of the argument string must be in yyyy/MM/dd format. parameter:" + format);
        }
        final String[] splitedStr = format.split("/");
        return new YearMonthDay(splitedStr[0], splitedStr[1], splitedStr[2]);
    }
    
    /**
     * Returns the day of the week for the date.
     *
     * @return The day of the week for the date.
     */
    public DayOfWeek toDayOfWeek() {
        return DayOfWeek.from(toLocalDate());
    }
    
    /**
     * Returns the LocalDate object representing the date.
     *
     * @return The LocalDate object representing the date.
     */
    public LocalDate toLocalDate() {
        return localDate;
    }
    
    /**
     * Checks if the year component of this date is equal to the given year.
     *
     * @param year the year to compare against
     * @return true if the year component is equal, false otherwise
     */
    public boolean equalsYear(final int year) {
        return this.localDate.getYear() == year;
    }
    
    /**
     * Checks if the month component of this date is equal to the given month.
     *
     * @param month the month to compare against
     * @return true if the month component is equal, false otherwise
     */
    public boolean equalsMonth(final int month) {
        return this.localDate.getMonthValue() == month;
    }
    
    /**
     * Checks if the day component of this date is equal to the given day.
     *
     * @param day the day to compare against
     * @return true if the day component is equal, false otherwise
     */
    public boolean equalsDay(final int day) {
        return this.localDate.getDayOfMonth() == (day);
    }
    
    /**
     * Checks if this date is a day off (i.e. a weekend or holiday).
     *
     * @return true if the date is a day off, false otherwise
     */
    public boolean isDayOff() {
        return isWeekend() || isHoliday();
    }
    
    /**
     * Checks if this date is a weekday (i.e. not a weekend or holiday).
     *
     * @return true if the date is a weekday, false otherwise
     */
    public boolean isWeekDay() {
        return !isWeekend();
    }
    
    /**
     * Checks if this date is a holiday in Japan.
     *
     * @return true if the date is a holiday, false otherwise
     */
    public boolean isHoliday() {
        return LocaleHoliday.of(Locale.getDefault()).isHoliday(this);
    }
    
    /**
     * Checks if this date is a weekend.
     *
     * @return true if the date is a weekend, false otherwise
     */
    public boolean isWeekend() {
        final DayOfWeek dayOfWeek = toDayOfWeek();
        return dayOfWeek == DayOfWeek.SUNDAY ||
                dayOfWeek == DayOfWeek.SATURDAY;
    }
}