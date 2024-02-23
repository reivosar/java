package reivosar.common.domain.model.time;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.util.lang.ObjectUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A value object that represents a specific date and time.
 */
public class DateTime extends ValueObject<DateTime> {
    
    /**
     * The underlying LocalDateTime object representing the date and time.
     */
    private final LocalDateTime value;

    /**
     * Constructs a new DateTime object with the specified date and time.
     *
     * @param value the date and time to represent
     */
    public DateTime(final LocalDateTime value) {
        ObjectUtil.requireNonNull("LocalDateTime", value);
        this.value = value;
    }

    /**
     * Formats the date and time as a string using the specified format string.
     *
     * @param format the format string
     * @return the formatted date and time string
     */
    public String formattedString(final String format) {
        ObjectUtil.requireNonNull("format", format);
        return value.format(DateTimeFormatter.ofPattern(format));
    }
}

