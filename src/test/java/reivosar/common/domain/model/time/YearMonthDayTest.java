package reivosar.common.domain.model.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class YearMonthDayTest {

    @Nested
    class ConstructorTest {

        @Test
        void shouldCreateYearMonthDayFromDateComponents() {
            YearMonthDay yearMonthDay = YearMonthDay.of(2024, 2, 21);
            Assertions.assertEquals(LocalDate.of(2024, 2, 21), yearMonthDay.toLocalDate());
        }

        @Test
        void shouldCreateYearMonthDayFromSlashFormattedString() {
            YearMonthDay yearMonthDay = YearMonthDay.fromSlashFormat("2024/02/21");
            Assertions.assertEquals(LocalDate.of(2024, 2, 21), yearMonthDay.toLocalDate());
        }

        @Test
        void shouldThrowExceptionForInvalidSlashFormattedString() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> YearMonthDay.fromSlashFormat("2024-02-21"));
        }
    }

    @Nested
    class IsHolidayTest {

        @Test
        void shouldReturnTrue_whenDateIsAHoliday() {
            YearMonthDay holidayDate = YearMonthDay.fromSlashFormat("2024/01/01");
            Assertions.assertTrue(holidayDate.isHoliday());
        }

        @Test
        void shouldReturnFalse_whenDateIsNotAHoliday() {
            YearMonthDay nonHolidayDate = YearMonthDay.fromSlashFormat("2024/01/02");
            Assertions.assertFalse(nonHolidayDate.isHoliday());
        }
    }

    @Nested
    class IsWeekendTest {

        @Test
        void shouldReturnTrue_whenDateIsSaturday() {
            YearMonthDay saturdayDate = YearMonthDay.fromSlashFormat("2024/01/06");
            Assertions.assertTrue(saturdayDate.isWeekend());
        }

        @Test
        void shouldReturnTrue_whenDateIsSunday() {
            YearMonthDay sundayDate = YearMonthDay.fromSlashFormat("2024/01/07");
            Assertions.assertTrue(sundayDate.isWeekend());
        }

        @Test
        void shouldReturnFalse_whenDateIsWeekday() {
            YearMonthDay weekdayDate = YearMonthDay.fromSlashFormat("2024/01/02");
            Assertions.assertFalse(weekdayDate.isWeekend());
        }
    }

    @Nested
    class EqualsYearMonthDayTest {

        @Test
        void shouldReturnTrue_whenYearIsEqual() {
            YearMonthDay date = YearMonthDay.of(2024, 2, 21);
            Assertions.assertTrue(date.equalsYear(2024));
        }

        @Test
        void shouldReturnFalse_whenYearIsNotEqual() {
            YearMonthDay date = YearMonthDay.of(2024, 2, 21);
            Assertions.assertFalse(date.equalsYear(2025));
        }
    }

    @Nested
    class EqualsMonthTest {

        @Test
        void shouldReturnTrue_whenMonthIsEqual() {
            YearMonthDay date = YearMonthDay.of(2024, 2, 21);
            Assertions.assertTrue(date.equalsMonth(2));
        }

        @Test
        void shouldReturnFalse_whenMonthIsNotEqual() {
            YearMonthDay date = YearMonthDay.of(2024, 2, 21);
            Assertions.assertFalse(date.equalsMonth(3));
        }
    }

    @Nested
    class EqualsDayTest {

        @Test
        void shouldReturnTrue_whenDayIsEqual() {
            YearMonthDay date = YearMonthDay.of(2024, 2, 21);
            Assertions.assertTrue(date.equalsDay(21));
        }

        @Test
        void shouldReturnFalse_whenDayIsNotEqual() {
            YearMonthDay date = YearMonthDay.of(2024, 2, 21);
            Assertions.assertFalse(date.equalsDay(22));
        }
    }

    @Nested
    class IsDayOffTest {

        @Test
        void shouldReturnTrue_whenDateIsWeekend() {
            YearMonthDay weekendDate = YearMonthDay.fromSlashFormat("2024/01/06");
            Assertions.assertTrue(weekendDate.isDayOff());
        }

        @Test
        void shouldReturnTrue_whenDateIsHoliday() {
            YearMonthDay holidayDate = YearMonthDay.fromSlashFormat("2024/01/01");
            Assertions.assertTrue(holidayDate.isDayOff());
        }

        @Test
        void shouldReturnFalse_whenDateIsWeekdayAndNotHoliday() {
            YearMonthDay weekdayDate = YearMonthDay.fromSlashFormat("2024/01/02");
            Assertions.assertFalse(weekdayDate.isDayOff());
        }
    }

    @Nested
    class IsWeekDayTest {

        @Test
        void shouldReturnTrue_whenDateIsWeekdayAndNotHoliday() {
            YearMonthDay weekdayDate = YearMonthDay.fromSlashFormat("2024/01/02");
            Assertions.assertTrue(weekdayDate.isWeekDay());
        }

        @Test
        void shouldReturnTrue_whenDateIsHoliday() {
            YearMonthDay holidayDate = YearMonthDay.fromSlashFormat("2024/01/01");
            Assertions.assertTrue(holidayDate.isWeekDay());
        }

        @Test
        void shouldReturnFalse_whenDateIsWeekend() {
            YearMonthDay weekendDate = YearMonthDay.fromSlashFormat("2024/01/06");
            Assertions.assertFalse(weekendDate.isWeekDay());
        }
    }
}
