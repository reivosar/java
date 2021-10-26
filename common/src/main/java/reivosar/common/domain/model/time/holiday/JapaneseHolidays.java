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

import reivosar.common.domain.model.time.YearMonthDay;
import reivosar.common.util.model.ValueObject;

public class JapaneseHolidays extends ValueObject<JapaneseHolidays>
{
	private static final Collection<Holiday> HOLIDAYS;
	private static final String FILE_URL = "https://www8.cao.go.jp/chosei/shukujitsu/syukujitsu.csv";

	static {
		final List<Holiday> holidaysList = new ArrayList<>();
		try (
			final BufferedInputStream bis = new BufferedInputStream (new URL(FILE_URL).openStream());
			final InputStreamReader isr   = new InputStreamReader   (bis, Charset.forName("MS932"));
			final BufferedReader br       = new BufferedReader      (isr))
		{
			if (br.readLine() != null) {
				br.lines()
					.filter  (line -> (line != null) && (line.split(",").length == 2))
					.map     (line -> resolveHoliday (line.split(",")))
					.forEach (holidaysList::add);
			}
			HOLIDAYS = Collections.unmodifiableList (holidaysList);		
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	private static Holiday resolveHoliday(String[] splitedlines) {
		return new Holiday(YearMonthDay.fromSlashFormat(splitedlines[0]), new HolidayName(splitedlines[1]));
	}

	public static Optional<Holiday> findHoliday(YearMonthDay yearMonthDay) {
		return HOLIDAYS.stream()
			.filter  (holiday -> holiday.yearMonthDay.equals(yearMonthDay))
			.findAny ();
	}
}
