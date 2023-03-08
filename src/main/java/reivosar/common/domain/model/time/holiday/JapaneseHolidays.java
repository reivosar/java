package reivosar.common.domain.model.time.holiday;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.common.util.lang.ObjectUtil;

/**
 * JapaneseHolidays class provides functions for finding holidays in Japan.
 */
public class JapaneseHolidays extends ValueObject<JapaneseHolidays> {
    /**
     * HOLIDAYS is a collection of Japanese holidays.
     */
    private static final Collection<Holiday> HOLIDAYS;
    
    /**
     * FILE_URL is the URL of a CSV file containing a list of Japanese holidays.
     */
    private static final String FILE_URL = "https://www8.cao.go.jp/chosei/shukujitsu/syukujitsu.csv";
    
    /*
     * The static initializer reads the CSV file and creates a list of Holiday objects.
     * The list is unmodifiable and is used for finding holidays.
     */
    static {
        final List<Holiday> holidaysList = new ArrayList<>();
        try (
                final BufferedInputStream bis = new BufferedInputStream(new URL(FILE_URL).openStream());
                final InputStreamReader isr = new InputStreamReader(bis, Charset.forName("MS932"));
                final BufferedReader br = new BufferedReader(isr)
        ) {
            if (br.readLine() != null) {
                br.lines()
                        .filter(line -> (line != null) && (line.split(",").length == 2))
                        .map(line -> resolveHoliday(line.split(",")))
                        .forEach(holidaysList::add);
            }
            HOLIDAYS = Collections.unmodifiableList(holidaysList);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    
    /*
     * The resolveHoliday method creates a Holiday object from a line of CSV data.
     */
    private static Holiday resolveHoliday(String[] splitedlines) {
        return new Holiday(YearMonthDay.fromSlashFormat(splitedlines[0]), new HolidayName(splitedlines[1]));
    }
    
    /**
     * The findHoliday method searches for a Holiday object with a given YearMonthDay.
     *
     * @param yearMonthDay the YearMonthDay to search for
     * @return an Optional containing the Holiday, or empty if not found
     */
    public static Optional<Holiday> findHoliday(final YearMonthDay yearMonthDay) {
        ObjectUtil.requireNonNull("YearMonthDay", yearMonthDay);
        return HOLIDAYS.stream()
                .filter(holiday -> holiday.yearMonthDay.equals(yearMonthDay))
                .findAny();
    }
}

