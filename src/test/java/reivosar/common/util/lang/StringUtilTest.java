package reivosar.common.util.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StringUtilTest {
    
    @Nested
    class DefaultIfNullOrEmptyTest {
        
        @Test
        void shouldReturnInputStringWhenNotEmpty() {
            String result = StringUtil.defaultIfNullOrEmpty("foo", "bar");
            Assertions.assertEquals("foo", result);
        }
        
        @Test
        void shouldReturnDefaultStringWhenInputStringIsNull() {
            String result = StringUtil.defaultIfNullOrEmpty(null, "bar");
            Assertions.assertEquals("bar", result);
        }
        
        @Test
        void shouldReturnDefaultStringWhenInputStringIsEmpty() {
            String result = StringUtil.defaultIfNullOrEmpty("", "bar");
            Assertions.assertEquals("bar", result);
        }
    }
    
    @Nested
    class IsEmptyTest {
        
        @Test
        void shouldReturnTrueWhenInputStringIsNull() {
            boolean result = StringUtil.isEmpty(null);
            Assertions.assertTrue(result);
        }
        
        @Test
        void shouldReturnTrueWhenInputStringIsEmpty() {
            boolean result = StringUtil.isEmpty("");
            Assertions.assertTrue(result);
        }
        
        @Test
        void shouldReturnFalseWhenInputStringIsNotEmpty() {
            boolean result = StringUtil.isEmpty("foo");
            Assertions.assertFalse(result);
        }
    }
    
    @Nested
    class IsNotEmptyTest {
        
        @Test
        void shouldReturnFalseWhenInputStringIsNull() {
            boolean result = StringUtil.isNotEmpty(null);
            Assertions.assertFalse(result);
        }
        
        @Test
        void shouldReturnFalseWhenInputStringIsEmpty() {
            boolean result = StringUtil.isNotEmpty("");
            Assertions.assertFalse(result);
        }
        
        @Test
        void shouldReturnTrueWhenInputStringIsNotEmpty() {
            boolean result = StringUtil.isNotEmpty("foo");
            Assertions.assertTrue(result);
        }
    }
}