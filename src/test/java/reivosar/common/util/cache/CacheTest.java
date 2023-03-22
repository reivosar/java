package reivosar.common.util.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    
    @Nested
    class TestForErrorCase {
        
        private final Cache<String, String> testClass = CacheFactory.getEternalLocalCache();
        
        @Test
        void shouldBeThrownExceptionWhenNullArgumentIsPassedToExistsMethod() {
            assertThrows(NullPointerException.class, () -> testClass.exists(null));
        }
        
        @Test
        void shouldBeThrownExceptionWhenNullArgumentIsPassedToGetMethod() {
            assertThrows(NullPointerException.class, () -> testClass.get(null));
        }
        
        @Test
        void shouldBeThrownExceptionWhenNullArgumentIsPassedToPutMethod() {
            assertThrows(NullPointerException.class, () -> testClass.put(null, null));
            assertThrows(NullPointerException.class, () -> testClass.put(null, "test"));
            assertThrows(NullPointerException.class, () -> testClass.put("key", null));
        }
        
        @Test
        void shouldBeThrownExceptionWhenNullArgumentIsPassedToClearMethod() {
            assertThrows(NullPointerException.class, () -> testClass.clear(null));
        }
    }
    
    @Nested
    class TestForNormalCase {
        
        private Cache<String, String> testClass;
        
        @BeforeEach
        void setup() {
            this.testClass = CacheFactory.getEternalLocalCache();
        }
        
        @Test
        void shouldBeNotGottenValuesWhenNoValueSetToCache() {
            // GIVEN
            final String key = "key";
            // final String value = "value";
            // WHEN
            // this.testClass.put(key, value);
            // THEN
            assertionEmptyValues(key);
            assertEquals(this.testClass.getAllKeys().size(), 0);
        }
        
        @Test
        void shouldBeGottenValuesWhenValueSetToCache() {
            // GIVEN
            final String key = "key";
            final String value = "value";
            // WHEN
            this.testClass.put(key, value);
            // THEN
            assertionNotEmptyValues(key, value);
        }
        
        @Test
        void shouldBeGottenValuesWhenValueSetToSameKeyInCache() {
            // GIVEN
            final String key = "key";
            final String value1 = "value1";
            final String value2 = "value2";
            // WHEN
            this.testClass.put(key, value1);
            this.testClass.put(key, value2);
            // THEN
            assertionNotEmptyValues(key, value1, value2);
            
            assertEquals(this.testClass.getAllKeys().size(), 1);
        }
        
        @Test
        void shouldBeGottenValuesWhenValueSetToAnyKeysInCache() {
            // GIVEN
            final String key1 = "key1";
            final String key2 = "key2";
            final String value1_1 = "value1_1";
            final String value1_2 = "value1_2";
            final String value2_1 = "value2_1";
            final String value2_2 = "value2_2";
            // WHEN
            this.testClass.put(key1, value1_1);
            this.testClass.put(key1, value1_2);
            this.testClass.put(key2, value2_1);
            this.testClass.put(key2, value2_2);
            // THEN
            assertionNotEmptyValues(key1, value1_1, value1_2);
            assertionNotEmptyValues(key2, value2_1, value2_2);
            
            assertEquals(2, this.testClass.getAllKeys().size());
        }
        
        @Test
        void shouldBeNotGottenValuesWhenClearMethodIsCalledWithKey() {
            // GIVEN
            final String key1 = "key1";
            final String value1 = "value1";
            final String key2 = "key2";
            final String value2 = "value2";
            // WHEN
            this.testClass.put(key1, value1);
            this.testClass.put(key2, value2);
            this.testClass.clear(key1);//@ATTN
            // THEN
            assertionEmptyValues(key1);
            assertionNotEmptyValues(key2, value2);
            
            assertEquals(this.testClass.getAllKeys().size(), 1);
        }
        
        @Test
        void shouldBeNotGottenValuesWhenClearAllMethodIsCalled() {
            // GIVEN
            final String key1 = "key1";
            final String value1 = "value1";
            final String key2 = "key2";
            final String value2 = "value2";
            // WHEN
            this.testClass.put(key1, value1);
            this.testClass.put(key2, value2);
            this.testClass.clearAll();
            // THEN
            assertionEmptyValues(key1);
            assertionEmptyValues(key2);
            
            assertEquals(this.testClass.getAllKeys().size(), 0);
        }
        
        private void assertionEmptyValues(String key) {
            assertFalse(this.testClass.exists(key));
            assertEquals(CacheValues.empty(), this.testClass.get(key));
            assertFalse(this.testClass.get(key).isNotEmpty());
            assertTrue(this.testClass.get(key).isEmpty());
            assertEquals(Optional.empty(), this.testClass.get(key).findFirst());
            assertIterableEquals(List.of(), this.testClass.get(key).values());
            assertEquals(0, this.testClass.get(key).size());
        }
        
        private void assertionNotEmptyValues(final String key, String... values) {
            assertTrue(this.testClass.exists(key));
            assertEquals(CacheValues.fromNativeCollection(new HashSet<>(Set.of(values))), this.testClass.get(key));
            assertTrue(this.testClass.get(key).isNotEmpty());
            assertFalse(this.testClass.get(key).isEmpty());
            assertEquals(Optional.of(values[0]), this.testClass.get(key).findFirst());
            assertIterableEquals(List.of(values), this.testClass.get(key).values());
            assertEquals(values.length, this.testClass.get(key).size());
        }
    }
}
