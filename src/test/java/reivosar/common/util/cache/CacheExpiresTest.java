package reivosar.common.util.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class CacheExpiresTest {
    
    @Nested
    class FromMonthsTest {
        
        @Test
        void shouldReturnCacheExpiresWithExpirationTimeInFuture_givenPositiveNumberOfMonths() {
            long months = 1;
            CacheExpires cacheExpires = CacheExpires.fromMonths(months);
            Assertions.assertTrue(cacheExpires.isAfterThanNow());
            Assertions.assertTrue(cacheExpires.isAfterNow());
        }
        
        @Test
        void shouldReturnCacheExpiresWithExpirationTimeInPast_givenNegativeNumberOfMonths() {
            long months = -1;
            CacheExpires cacheExpires = CacheExpires.fromMonths(months);
            Assertions.assertTrue(cacheExpires.isBeforeThanNow());
            Assertions.assertTrue(cacheExpires.isBeforeNow());
        }
        
    }
    
    @Nested
    class FromDaysTest {
        
        @Test
        void shouldReturnCacheExpiresWithExpirationTimeInFuture_givenPositiveNumberOfDays() {
            long days = 1;
            CacheExpires cacheExpires = CacheExpires.fromDays(days);
            Assertions.assertTrue(cacheExpires.isAfterThanNow());
            Assertions.assertTrue(cacheExpires.isAfterNow());
        }
        
        @Test
        void shouldReturnCacheExpiresWithExpirationTimeInPast_givenNegativeNumberOfDays() {
            long days = -1;
            CacheExpires cacheExpires = CacheExpires.fromDays(days);
            Assertions.assertTrue(cacheExpires.isBeforeThanNow());
            Assertions.assertTrue(cacheExpires.isBeforeNow());
        }
        
    }
    
    @Nested
    class FromHoursTest {
        
        @Test
        void shouldReturnCacheExpiresWithExpirationTimeInFuture_givenPositiveNumberOfHours() {
            long hours = 1;
            CacheExpires cacheExpires = CacheExpires.fromHours(hours);
            Assertions.assertTrue(cacheExpires.isAfterThanNow());
            Assertions.assertTrue(cacheExpires.isAfterNow());
        }
        
        @Test
        void shouldReturnCacheExpiresWithExpirationTimeInPast_givenNegativeNumberOfHours() {
            long hours = -1;
            CacheExpires cacheExpires = CacheExpires.fromHours(hours);
            Assertions.assertTrue(cacheExpires.isBeforeThanNow());
            Assertions.assertTrue(cacheExpires.isBeforeNow());
        }
        
    }
    
    @Nested
    class FromMinutesTest {
        
        @Test
        void shouldReturnCacheExpiresWithExpirationTimeInFuture_givenPositiveNumberOfMinutes() {
            long minutes = 1;
            CacheExpires cacheExpires = CacheExpires.fromMinutes(minutes);
            Assertions.assertTrue(cacheExpires.isAfterThanNow());
            Assertions.assertTrue(cacheExpires.isAfterNow());
        }
        
        @Test
        void shouldReturnCacheExpiresWithExpirationTimeInPast_givenNegativeNumberOfMinutes() {
            long minutes = -1;
            CacheExpires cacheExpires = CacheExpires.fromMinutes(minutes);
            Assertions.assertTrue(cacheExpires.isBeforeThanNow());
            Assertions.assertTrue(cacheExpires.isBeforeNow());
        }
    }
    
    @Nested
    class SameLocalDateTest {
    
        @Test
        void shouldReturnCacheExpiresWithExpirationTimeInPast_givenNegativeNumberOfMinutes() {
            LocalDateTime localDateTime = LocalDateTime.now();
            CacheExpires cacheExpires = CacheExpires.from(localDateTime);
            Assertions.assertTrue(cacheExpires.isBeforeThan(localDateTime));
            Assertions.assertFalse(cacheExpires.isBefore(localDateTime));
            Assertions.assertTrue(cacheExpires.isAfterThan(localDateTime));
            Assertions.assertFalse(cacheExpires.isAfter(localDateTime));
        }
    }
}