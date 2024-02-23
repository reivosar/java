package reivosar.common.domain.model.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateTimeTest {

    @Test
    void formattedString_shouldReturnFormattedDateTimeString() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 2, 21, 12, 30);
        DateTime dt = new DateTime(dateTime);
        String formattedString = dt.formattedString("yyyy/MM/dd HH:mm");
        Assertions.assertEquals("2024/02/21 12:30", formattedString);
    }
}