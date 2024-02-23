package reivosar.common.domain.model.time;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import java.time.LocalTime;

class HourMinuteTest {

    @Nested
    class ConstructorTest {

        @Test
        void shouldCreateHourMinuteObjectWithValidValues() {
            HourMinute hourMinute = new HourMinute(10, 30);
            Assertions.assertEquals(LocalTime.of(10, 30), hourMinute.toLocalTime());
        }

        @Test
        void shouldThrowExceptionForInvalidHour() {
            Assertions.assertThrows(
                    java.time.DateTimeException.class,
                    () -> new HourMinute(24, 0)
            );
        }

        @Test
        void shouldThrowExceptionForInvalidMinute() {
            Assertions.assertThrows(
                    java.time.DateTimeException.class,
                    () -> new HourMinute(0, 60)
            );
        }
    }

    @Nested
    class EqualsHourTest {

        @Test
        void shouldReturnTrueWhenHourIsEqual() {
            HourMinute hourMinute = new HourMinute(10, 30);
            Assertions.assertTrue(hourMinute.equalsHour(10));
        }

        @Test
        void shouldReturnFalseWhenHourIsNotEqual() {
            HourMinute hourMinute = new HourMinute(10, 30);
            Assertions.assertFalse(hourMinute.equalsHour(11));
        }
    }

    @Nested
    class EqualsMinuteTest {

        @Test
        void shouldReturnTrueWhenMinuteIsEqual() {
            HourMinute hourMinute = new HourMinute(10, 30);
            Assertions.assertTrue(hourMinute.equalsMinute(30));
        }

        @Test
        void shouldReturnFalseWhenMinuteIsNotEqual() {
            HourMinute hourMinute = new HourMinute(10, 30);
            Assertions.assertFalse(hourMinute.equalsMinute(45));
        }
    }

    @Nested
    class IsBeforeTest {

        @Test
        void shouldReturnTrueWhenHourMinuteIsBefore() {
            HourMinute hourMinute1 = new HourMinute(10, 30);
            HourMinute hourMinute2 = new HourMinute(11, 0);
            Assertions.assertTrue(hourMinute1.isBefore(hourMinute2));
        }

        @Test
        void shouldReturnFalseWhenHourMinuteIsNotBefore() {
            HourMinute hourMinute1 = new HourMinute(10, 30);
            HourMinute hourMinute2 = new HourMinute(9, 0);
            Assertions.assertFalse(hourMinute1.isBefore(hourMinute2));
        }
    }

    @Nested
    class IsBeforeThanTest {

        @Test
        void shouldReturnTrueWhenHourMinuteIsBeforeOrEqual() {
            HourMinute hourMinute1 = new HourMinute(10, 30);
            HourMinute hourMinute2 = new HourMinute(11, 0);
            Assertions.assertTrue(hourMinute1.isBeforeThan(hourMinute2));
            Assertions.assertTrue(hourMinute1.isBeforeThan(new HourMinute(10, 30)));
        }

        @Test
        void shouldReturnFalseWhenHourMinuteIsAfter() {
            HourMinute hourMinute1 = new HourMinute(10, 30);
            HourMinute hourMinute2 = new HourMinute(9, 0);
            Assertions.assertFalse(hourMinute1.isBeforeThan(hourMinute2));
        }
    }

    @Nested
    class IsAfterTest {

        @Test
        void shouldReturnTrueWhenHourMinuteIsAfter() {
            HourMinute hourMinute1 = new HourMinute(11, 0);
            HourMinute hourMinute2 = new HourMinute(10, 30);
            Assertions.assertTrue(hourMinute1.isAfter(hourMinute2));
        }

        @Test
        void shouldReturnFalseWhenHourMinuteIsNotAfter() {
            HourMinute hourMinute1 = new HourMinute(9, 0);
            HourMinute hourMinute2 = new HourMinute(10, 30);
            Assertions.assertFalse(hourMinute1.isAfter(hourMinute2));
        }
    }

    @Nested
    class IsAfterThanTest {

        @Test
        void shouldReturnTrueWhenHourMinuteIsAfterOrEqual() {
            HourMinute hourMinute1 = new HourMinute(11, 0);
            HourMinute hourMinute2 = new HourMinute(10, 30);
            Assertions.assertTrue(hourMinute1.isAfterThan(hourMinute2));
            Assertions.assertTrue(hourMinute1.isAfterThan(new HourMinute(11, 0)));
        }

        @Test
        void shouldReturnFalseWhenHourMinuteIsBefore() {
            HourMinute hourMinute1 = new HourMinute(9, 0);
            HourMinute hourMinute2 = new HourMinute(10, 30);
            Assertions.assertFalse(hourMinute1.isAfterThan(hourMinute2));
        }
    }
}
