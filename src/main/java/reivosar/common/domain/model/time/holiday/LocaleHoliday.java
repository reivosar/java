package reivosar.common.domain.model.time.holiday;

import reivosar.common.domain.model.time.YearMonthDay;

import java.util.Arrays;
import java.util.Locale;

/**
 * The {@code CountryHoliday} enum represents holidays specific to a country or region.
 * Each enum constant associates a {@code Locale} with a set of holidays.
 */
public enum LocaleHoliday {

    /**
     * Represents Japanese holidays.
     */
    JAPANESE(Locale.JAPANESE, new JapaneseHolidays());

    private final Locale locale;
    private final Holidays holidays;

    /**
     * Constructs a {@code CountryHoliday} with the specified locale and holidays.
     *
     * @param locale   the locale associated with the country or region
     * @param holidays the holidays specific to the country or region
     */
    LocaleHoliday(final Locale locale, final Holidays holidays) {
        this.locale = locale;
        this.holidays = holidays;
    }

    /**
     * Returns the {@code CountryHoliday} enum constant associated with the specified locale.
     *
     * @param locale the locale for which to retrieve the corresponding {@code CountryHoliday}
     * @return the {@code CountryHoliday} associated with the specified locale
     * @throws IllegalArgumentException if no enum constant is associated with the specified locale
     */
    public static LocaleHoliday of(final Locale locale) {
        return Arrays.stream(values())
                .filter(ch -> ch.locale.getLanguage().equals(locale.getLanguage()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported locale: " + locale));
    }

    /**
     * Checks if the specified date is a holiday for the country or region represented by this enum constant.
     *
     * @param yearMonthDay the year-month-day representing the date to check
     * @return {@code true} if the specified date is a holiday, otherwise {@code false}
     */
    public boolean isHoliday(final YearMonthDay yearMonthDay) {
        return holidays.isHoliday(yearMonthDay);
    }
}