package reivosar.common.domain.model.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import reivosar.common.util.model.ValueObject;

public class DateTime extends ValueObject<DateTime>
{
	private final LocalDateTime value;

	public DateTime(LocalDateTime value) {
		this.value = value;
	}

	public static DateTime now() {
		return new DateTime(LocalDateTime.now());
	}

    public LocalDateTime toLocalDateTime() {
        return value;
    }

	public String formattedString(String format) {
		return value.format(DateTimeFormatter.ofPattern(format));
	}
}
