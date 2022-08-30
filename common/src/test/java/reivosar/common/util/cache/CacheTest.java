package reivosar.common.util.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CacheTest {
    
    @Nested
    class TestForErrorCase {
        
        private final Cache<String, String> testClass = Cache.getEternalLocalCache();
        
        @Test
        void shouldBeThrownException_when_null_argument_is_passed_to_existsMethod() {
            assertThrows(NullPointerException.class, () -> testClass.exists(null));
        }
        
        @Test
        void shouldBeThrownException_when_null_argument_is_passed_to_getMethod() {
            assertThrows(NullPointerException.class, () -> testClass.get(null));
        }
        
        @Test
        void shouldBeThrownException_when_null_argument_is_passed_to_putMethod() {
            assertThrows(NullPointerException.class, () -> testClass.put(null, null));
            assertThrows(NullPointerException.class, () -> testClass.put(null, "test"));
            assertThrows(NullPointerException.class, () -> testClass.put("key",  null));
        }
        
        @Test
        void shouldBeThrownException_when_null_argument_is_passed_to_clearMethod() {
            assertThrows(NullPointerException.class, () -> testClass.clear(null));
        }
    }
    
    @Nested
    class TestForNormalCase {
        
        private Cache<String, String> testClass;
        
        @BeforeEach
        void setup() {
            this.testClass = Cache.getEternalLocalCache();
        }
        
        @Test
        void shouldBeNotGottenValues_when_no_value_set_to_cache() {
            // GIVEN
            final String key = "key";
            final String value = "value";
            // WHEN
            // this.testClass.put(key, value);
            // THEN
            assertThat(this.testClass.exists(key), is(false));
            assertThat(this.testClass.get(key), is(CacheValues.empty()));
            assertThat(this.testClass.getAllKeys().size(), is(0));
        }
        
        @Test
        void shouldBeGottenValues_when_value_set_to_cache() {
            // GIVEN
            final String key = "key";
            final String value = "value";
            // WHEN
            this.testClass.put(key, value);
            // THEN
            assertThat(this.testClass.exists(key), is(true));
            assertThat(this.testClass.get(key), is(new CacheValues<>(List.of(value))));
            assertThat(this.testClass.getAllKeys().size(), is(1));
        }
    
        @Test
        void shouldBeGottenValues_when_value_set_to_same_key_in_cache() {
            // GIVEN
            final String key = "key";
            final String value1 = "value1";
            final String value2 = "value2";
            // WHEN
            this.testClass.put(key, value1);
            this.testClass.put(key, value2);
            // THEN
            assertThat(this.testClass.exists(key), is(true));
            assertThat(this.testClass.get(key), is(new CacheValues<>(List.of(value1, value2))));
            assertThat(this.testClass.getAllKeys().size(), is(1));
        }
    
        @Test
        void shouldBeGottenValues_when_value_set_to_any_keys_in_cache() {
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
            assertThat(this.testClass.exists(key1), is(true));
            assertThat(this.testClass.get(key1), is(new CacheValues<>(List.of(value1_1, value1_2))));
            assertThat(this.testClass.exists(key2), is(true));
            assertThat(this.testClass.get(key2), is(new CacheValues<>(List.of(value2_1, value2_2))));
            assertThat(this.testClass.getAllKeys().size(), is(2));
        }
    
        @Test
        void shouldBeNotGottenValues_when_clear_method_is_called_with_key() {
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
            assertThat(this.testClass.exists(key1), is(false));
            assertThat(this.testClass.get(key1), is(CacheValues.empty()));
            assertThat(this.testClass.exists(key2), is(true));
            assertThat(this.testClass.get(key2), is(new CacheValues<>(List.of(value2))));
            assertThat(this.testClass.getAllKeys().size(), is(1));
        }
    
        @Test
        void shouldBeNotGottenValues_when_clearAll_method_is_called() {
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
            assertThat(this.testClass.exists(key1), is(false));
            assertThat(this.testClass.get(key1), is(CacheValues.empty()));
            assertThat(this.testClass.exists(key2), is(false));
            assertThat(this.testClass.get(key2), is(CacheValues.empty()));
            assertThat(this.testClass.getAllKeys().size(), is(0));
        }
    }
}
