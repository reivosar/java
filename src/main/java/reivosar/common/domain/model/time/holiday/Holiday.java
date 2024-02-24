package reivosar.common.domain.model.time.holiday;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.common.util.lang.ObjectUtil;

/**
 * A value object representing a holiday with a specific date and name.
 */
public class Holiday extends ValueObject<Holiday> {
    
    /**
     * The date of the holiday.
     */
    final YearMonthDay yearMonthDay;
    
    /**
     * The name of the holiday.
     */
    final HolidayName holidayName;
    
    /**
     * Constructs a new `Holiday` object with the given date and name.
     *
     * @param yearMonthDay The date of the holiday.
     * @param holidayName  The name of the holiday.
     */
    public Holiday(final YearMonthDay yearMonthDay, final HolidayName holidayName) {
        this.yearMonthDay = ObjectUtil.requireNonNull("yearMonthDay", yearMonthDay);
        this.holidayName = ObjectUtil.requireNonNull("holidayName", holidayName);
    }
}