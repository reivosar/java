package reivosar.common.util.cache;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CacheValuesTest {
    
    @Nested
    class ConstructorTests {
        
        @Test
        void shouldThrowExceptionWhenGivenNull() {
            Assertions.assertThrows(NullPointerException.class, () -> new CacheValues<>(null));
        }
        
        @Test
        void shouldCreateCacheValuesObjectWhenGivenEmptyCollection() {
            CacheValues<Object> cacheValues = new CacheValues<>(new ArrayList<>());
            Assertions.assertNotNull(cacheValues);
        }
        
        @Test
        void shouldCreateCacheValuesObjectWhenGivenNonEmptyCollection() {
            Collection<Integer> values = Collections.unmodifiableCollection(List.of(1, 2, 3));
            CacheValues<Integer> cacheValues = new CacheValues<>(values);
            Assertions.assertNotNull(cacheValues);
            Assertions.assertEquals(cacheValues.values(), values);
        }
    }
    
    @Nested
    class EmptyMethodTests {
        @Test
        void shouldReturnEmptyCacheValuesObject() {
            CacheValues<Object> cacheValues = CacheValues.empty();
            Assertions.assertNotNull(cacheValues);
            Assertions.assertTrue(cacheValues.isEmpty());
        }
    }
    
    @Nested
    class ValuesMethodTests {
        private Collection<String> valuesList;
        private CacheValues<String> cacheValues;
        
        @BeforeEach
        void setup() {
            valuesList= Collections.unmodifiableCollection(List.of("a", "b", "c"));
            cacheValues = new CacheValues<>(valuesList);
        }
        
        @Test
        void shouldReturnUnmodifiableCollectionOfCachedValues() {
            Collection<String> values = cacheValues.values();
            Assertions.assertNotNull(values);
            Assertions.assertThrows(UnsupportedOperationException.class, () -> values.add("d"));
        }
        
        @Test
        void shouldReturnCachedValues() {
            Collection<String> values = cacheValues.values();
            Assertions.assertNotNull(values);
            Assertions.assertEquals(values, valuesList);
        }
    }
    
    @Nested
    class FindFirstMethodTests {
        
        private CacheValues<String> cacheValues;
        
        @BeforeEach
        void setup() {
            List<String> valuesList = List.of("a", "b", "c");
            cacheValues = new CacheValues<>(valuesList);
        }
        
        @Test
        void shouldReturnOptionalWithFirstCachedValue() {
            String firstValue = cacheValues.findFirst().orElse(null);
            Assertions.assertNotNull(firstValue);
            Assertions.assertEquals(firstValue, "a");
        }
        
        @Test
        void shouldReturnEmptyOptionalIfNoValuesCached() {
            CacheValues<String> emptyCacheValues = CacheValues.empty();
            Assertions.assertTrue(emptyCacheValues.findFirst().isEmpty());
        }
    }
    
    @Nested
    class ValueMethodTests {
        
        private CacheValues<String> cacheValues;
        
        @BeforeEach
        void setup() {
            List<String> valuesList = List.of("a", "b", "c");
            cacheValues = new CacheValues<>(valuesList);
        }
        
        @Test
        void shouldReturnValueIfValuesCached() {
            String firstValue = cacheValues.value();
            Assertions.assertNotNull(firstValue);
            Assertions.assertEquals(firstValue, "a");
        }
        
        @Test
        void shouldThrowNullPointerExceptionIfNoValuesCached() {
            CacheValues<String> emptyCacheValues = CacheValues.empty();
            Assertions.assertThrows(NullPointerException.class, emptyCacheValues::value);
        }
    }
    
    @Nested
    class NullableValueTests {
        @Test
        void shouldReturnFirstValueWhenValuesExist() {
            // GIVEN
            List<Integer> values = List.of(1, 2, 3);
            CacheValues<Integer> cache = new CacheValues<>(values);
            // WHEN
            Integer result = cache.nullableValue();
            // THEN
            assertEquals(1, result);
        }
        
        @Test
        void shouldReturnDefaultValueWhenNoValuesExist() {
            // GIVEN
            CacheValues<String> cache = CacheValues.empty();
            // WHEN
            String result = cache.nullableValue();
            // THEN
            assertNull(result);
        }
    }
    
    @Nested
    class OrElseTests {
        @Test
        void shouldReturnFirstValueWhenValuesExist() {
            // GIVEN
            List<Integer> values = List.of(1, 2, 3);
            CacheValues<Integer> cache = new CacheValues<>(values);
            // WHEN
            int result = cache.orElse(0);
            // THEN
            assertEquals(1, result);
        }
        
        @Test
        void shouldReturnDefaultValueWhenNoValuesExist() {
            // GIVEN
            CacheValues<Integer> cache = CacheValues.empty();
            // WHEN
            int result = cache.orElse(0);
            // THEN
            assertEquals(0, result);
        }
    }
    
    @Nested
    class IsNotEmptyTests {
        @Test
        void shouldReturnTrueWhenValuesExist() {
            // GIVEN
            List<Integer> values = List.of(1, 2, 3);
            CacheValues<Integer> cache = new CacheValues<>(values);
            // WHEN
            boolean result = cache.isNotEmpty();
            // THEN
            assertTrue(result);
        }
        
        @Test
        void shouldReturnFalseWhenNoValuesExist() {
            // GIVEN
            CacheValues<Integer> cache = CacheValues.empty();
            // WHEN
            boolean result = cache.isNotEmpty();
            // THEN
            assertFalse(result);
        }
    }
    
    @Nested
    class IsEmptyTests {
        @Test
        void shouldReturnFalseWhenValuesExist() {
            // GIVEN
            List<Integer> values = List.of(1, 2, 3);
            CacheValues<Integer> cache = new CacheValues<>(values);
            // WHEN
            boolean result = cache.isEmpty();
            // THEN
            assertFalse(result);
        }
        
        @Test
        void shouldReturnTrueWhenNoValuesExist() {
            // GIVEN
            CacheValues<Integer> cache = CacheValues.empty();
            // WHEN
            boolean result = cache.isEmpty();
            // THEN
            assertTrue(result);
        }
    }
    
    @Nested
    class SizeTests {
        @Test
        void shouldReturnNumberOfValuesWhenValuesExist() {
            // GIVEN
            List<Integer> values = List.of(1, 2, 3);
            CacheValues<Integer> cache = new CacheValues<>(values);
            // WHEN
            int result = cache.size();
            // THEN
            assertEquals(3, result);
        }
        
        @Test
        void shouldReturnZeroWhenNoValuesExist() {
            // GIVEN
            CacheValues<Integer> cache = CacheValues.empty();
            // WHEN
            int result = cache.size();
            // THEN
            assertEquals(0, result);
        }
    }
}
