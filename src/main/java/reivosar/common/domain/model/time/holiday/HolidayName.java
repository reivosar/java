package reivosar.common.domain.model.time.holiday;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.lang.ObjectUtil;

/**
 * Value object representing the name of a holiday.
 */
public class HolidayName extends ValueObject<HolidayName> {
    
    /**
     * The string value representing the name of the holiday.
     */
    final String value;
    
    /**
     * Constructs a new HolidayName object with the specified name.
     *
     * @param name the name of the holiday, must not be null
     * @throws NullPointerException if the name parameter is null
     */
    public HolidayName(final String name) {
        this.value = ObjectUtil.requireNonNull("name", name);
    }
}
