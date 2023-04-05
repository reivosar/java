package reivosar.common.domain.model.time;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.util.lang.ObjectUtil;

import java.time.LocalTime;

/**
 * Value object representing an hour and minute time of day.
 */
public class HourMinute extends ValueObject<HourMinute> {
    
    /**
     * The local time represented by this hour and minute.
     */
    final LocalTime localTime;
    
    /**
     * Constructs a new instance with the given hour and minute values.
     *
     * @param hour   the hour value (0-23)
     * @param minute the minute value (0-59)
     */
    public HourMinute(final int hour, final int minute) {
        this.localTime = LocalTime.of(hour, minute);
    }
    
    /**
     * Returns the local time representation of this hour and minute.
     *
     * @return the local time representation
     */
    public LocalTime toLocalTime() {
        return localTime;
    }
    
    /**
     * Checks if this instance has the same hour value as the given hour.
     *
     * @param hour the hour value to compare against
     * @return true if the hour values are equal, false otherwise
     */
    public boolean equalsHour(final int hour) {
        return this.localTime.getHour() == hour;
    }
    
    /**
     * Checks if this instance has the same minute value as the given minute.
     *
     * @param minute the minute value to compare against
     * @return true if the minute values are equal, false otherwise
     */
    public boolean equalsMinute(final int minute) {
        return this.localTime.getMinute() == minute;
    }
    
    /**
     * Checks if this instance is before the given hour and minute.
     *
     * @param hm the hour and minute to compare against
     * @return true if this instance is before the given hour and minute, false otherwise
     */
    public boolean isBefore(final HourMinute hm) {
        ObjectUtil.requireNonNull("HourMinute", hm);
        return toLocalTime().isBefore(hm.toLocalTime());
    }
    
    /**
     * Checks if this instance is before or the same as the given hour and minute.
     *
     * @param hm the hour and minute to compare against
     * @return true if this instance is before or the same as the given hour and minute, false otherwise
     */
    public boolean isBeforeThan(final HourMinute hm) {
        ObjectUtil.requireNonNull("HourMinute", hm);
        return toLocalTime().compareTo(hm.toLocalTime()) <= 0;
    }
    
    /**
     * Checks if this instance is after the given hour and minute.
     *
     * @param hm the hour and minute to compare against
     * @return true if this instance is after the given hour and minute, false otherwise
     */
    public boolean isAfter(final HourMinute hm) {
        ObjectUtil.requireNonNull("HourMinute", hm);
        return toLocalTime().isAfter(hm.toLocalTime());
    }
    
    /**
     * Checks if this instance is after or the same as the given hour and minute.
     *
     * @param hm the hour and minute to compare against
     * @return true if this instance is after or the same as the given hour and minute, false otherwise
     */
    public boolean isAfterThan(final HourMinute hm) {
        ObjectUtil.requireNonNull("HourMinute", hm);
        return toLocalTime().compareTo(hm.toLocalTime()) >= 0;
    }
}
