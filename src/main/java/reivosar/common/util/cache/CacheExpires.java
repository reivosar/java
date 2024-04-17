package reivosar.common.util.cache;

import reivosar.common.lang.ObjectUtil;
import reivosar.common.data.model.Model;

import java.time.LocalDateTime;

/**
 * A model class representing the expiration time for a cache entry.
 */
public final class CacheExpires extends Model {
    
    public static CacheExpires EXTERNAl = new CacheExpires(LocalDateTime.MAX);
    
    private final LocalDateTime localDateTime;
    
    /**
     * Returns a new instance of {@link CacheExpires} with an expiration time
     * calculated by adding the specified number of months to the current date and time.
     *
     * @param months the number of months to add to the current date and time
     * @return a new instance of {@link CacheExpires} with the calculated expiration time
     */
    public static CacheExpires fromMonths(final long months) {
        return new CacheExpires(LocalDateTime.now().plusMonths(months));
    }
    
    /**
     * Returns a new instance of {@link CacheExpires} with an expiration time
     * calculated by adding the specified number of days to the current date and time.
     *
     * @param days the number of days to add to the current date and time
     * @return a new instance of {@link CacheExpires} with the calculated expiration time
     */
    public static CacheExpires fromDays(final long days) {
        return new CacheExpires(LocalDateTime.now().plusDays(days));
    }
    
    /**
     * Returns a new instance of {@link CacheExpires} with an expiration time
     * calculated by adding the specified number of hours to the current date and time.
     *
     * @param hours the number of hours to add to the current date and time
     * @return a new instance of {@link CacheExpires} with the calculated expiration time
     */
    public static CacheExpires fromHours(final long hours) {
        return new CacheExpires(LocalDateTime.now().plusHours(hours));
    }
    
    /**
     * Returns a new instance of {@link CacheExpires} with an expiration time
     * calculated by adding the specified number of minutes to the current date and time.
     *
     * @param minutes the number of minutes to add to the current date and time
     * @return a new instance of {@link CacheExpires} with the calculated expiration time
     */
    public static CacheExpires fromMinutes(final long minutes) {
        return new CacheExpires(LocalDateTime.now().plusMinutes(minutes));
    }
    
    /**
     * Returns a new instance of {@link CacheExpires} with an expiration time
     *
     * @param localDateTime the localDateTime
     * @return a new instance of {@link CacheExpires} with the calculated expiration time
     */
    public static CacheExpires from(final LocalDateTime localDateTime) {
        return new CacheExpires(localDateTime);
    }
    
    private CacheExpires(final LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
    
    /**
     * Checks if this instance is before the given hour and minute.
     *
     * @return true if this instance is before the given hour and minute, false otherwise
     */
    public boolean isBeforeNow() {
        return isBefore(LocalDateTime.now());
    }
    
    /**
     * Checks if this instance is before the given hour and minute.
     *
     * @param localDateTime the localDateTime to compare against
     * @return true if this instance is before the given hour and minute, false otherwise
     */
    public boolean isBefore(final LocalDateTime localDateTime) {
        ObjectUtil.requireNonNull("localDateTime", localDateTime);
        return this.localDateTime.isBefore(localDateTime);
    }
    
    /**
     * Checks if this instance is before or the same as the given hour and minute.
     *
     * @return true if this instance is before or the same as the given hour and minute, false otherwise
     */
    public boolean isBeforeThanNow() {
        return isBeforeThan(LocalDateTime.now());
    }
    
    /**
     * Checks if this instance is before or the same as the given hour and minute.
     *
     * @param localDateTime the localDateTime to compare against
     * @return true if this instance is before or the same as the given hour and minute, false otherwise
     */
    public boolean isBeforeThan(final LocalDateTime localDateTime) {
        ObjectUtil.requireNonNull("localDateTime", localDateTime);
        return !this.localDateTime.isAfter(localDateTime);
    }
    
    /**
     * Checks if this instance is after the given hour and minute.
     *
     * @return true if this instance is after the given hour and minute, false otherwise
     */
    public boolean isAfterNow() {
        return isAfter(LocalDateTime.now());
    }
    
    /**
     * Checks if this instance is after the given hour and minute.
     *
     * @param localDateTime the localDateTime to compare against
     * @return true if this instance is after the given hour and minute, false otherwise
     */
    public boolean isAfter(final LocalDateTime localDateTime) {
        ObjectUtil.requireNonNull("localDateTime", localDateTime);
        return this.localDateTime.isAfter(localDateTime);
    }
    
    /**
     * Checks if this instance is after or the same as the given hour and minute.
     *
     * @return true if this instance is after or the same as the given hour and minute, false otherwise
     */
    public boolean isAfterThanNow() {
        return isAfterThan(LocalDateTime.now());
    }
    
    /**
     * Checks if this instance is after or the same as the given hour and minute.
     *
     * @param localDateTime the localDateTime to compare against
     * @return true if this instance is after or the same as the given hour and minute, false otherwise
     */
    public boolean isAfterThan(final LocalDateTime localDateTime) {
        ObjectUtil.requireNonNull("localDateTime", localDateTime);
        return !this.localDateTime.isBefore(localDateTime);
    }
}
