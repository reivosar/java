package reivosar.common.util.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    
    @Nested
    class TestForErrorCase {
        
        private final Cache<String, String> testClass = CacheFactory.getEternalLocalCache();
        
        @Test
        void shouldBeThrownException_when_nullArgumentIsPassedToExistsMethod() {
            assertThrows(NullPointerException.class, () -> testClass.exists(null));
        }
        
        @Test
        void shouldBeThrownException_when_nullArgumentIsPassedToGetMethod() {
            assertThrows(NullPointerException.class, () -> testClass.get(null));
        }
        
        @Test
        void shouldBeThrownException_when_nullArgumentIsPassedToPutMethod() {
            assertThrows(NullPointerException.class, () -> testClass.put(null, null));
            assertThrows(NullPointerException.class, () -> testClass.put(null, "test"));
            assertThrows(NullPointerException.class, () -> testClass.put("key", null));
        }
        
        @Test
        void shouldBeThrownException_when_nullArgumentIsPassedToClearMethod() {
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
        void shouldBeNotGottenValues_whenNoValueSetToCache() {
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
        void shouldBeGottenValues_when_valueSetToCache() {
            // GIVEN
            final String key = "key";
            final String value = "value";
            // WHEN
            this.testClass.put(key, value);
            // THEN
            assertionNotEmptyValues(key, value);
        }
        
        @Test
        void shouldBeGottenValues_when_valueSetToSameKeyInCache() {
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
        void shouldBeGottenValues_when_valueSetToAnyKeysInCache() {
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
        void shouldBeNotGottenValues_when_clearMethodIsCalledWithKey() {
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
        void shouldBeNotGottenValues_when_clearAllMethodIsCalled() {
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
            assertEquals(Optional.empty(), this.testClass.get(key).first());
            assertIterableEquals(List.of(), this.testClass.get(key).all());
            assertEquals(0, this.testClass.get(key).count());
        }
        
        private void assertionNotEmptyValues(final String key, String... values) {
            assertTrue(this.testClass.exists(key));
            assertEquals(new CacheValues<>(Set.of(values)), this.testClass.get(key));
            assertTrue(this.testClass.get(key).isNotEmpty());
            assertFalse(this.testClass.get(key).isEmpty());
            assertEquals(Optional.of(values[0]), this.testClass.get(key).first());
            assertIterableEquals(List.of(values), this.testClass.get(key).all());
            assertEquals(values.length, this.testClass.get(key).count());
        }
    }
}
