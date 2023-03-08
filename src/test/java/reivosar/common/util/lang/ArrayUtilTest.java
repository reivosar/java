package reivosar.common.util.lang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ArrayUtilTest {
    
    @Nested
    class NullToEmptyTests {
        
        @Test
        void shouldReturnEmptyArrayIfGivenNull() {
            Object[] result = ArrayUtil.nullToEmpty((Object[]) null);
            Assertions.assertArrayEquals(new Object[]{}, result);
        }
        
        @Test
        void shouldReturnSameArrayIfGivenNonNullArray() {
            Object[] input = new Object[]{"a", "b", "c"};
            Object[] result = ArrayUtil.nullToEmpty(input);
            Assertions.assertSame(input, result);
        }
        
        @Test
        void shouldReturnNewArrayInstanceIfGivenEmptyArray() {
            Object[] input = new Object[]{};
            Object[] result = ArrayUtil.nullToEmpty(input);
            Assertions.assertNotSame(input, result);
            Assertions.assertArrayEquals(input, result);
        }
        
        @Test
        void shouldHandleMultipleNullValues() {
            Object[] input = new Object[]{null, null, null};
            Object[] expected = new Object[]{null, null, null};
            Object[] result = ArrayUtil.nullToEmpty(input);
            Assertions.assertEquals(3, result.length);
            Assertions.assertArrayEquals(expected, result);
        }
        
        @Test
        void shouldHandleNonNullAndNullValues() {
            Object[] input = new Object[]{null, "a", null, "b", null};
            Object[] expected = new Object[]{null, "a", null, "b", null};
            Object[] result = ArrayUtil.nullToEmpty(input);
            Assertions.assertEquals(5, result.length);
            Assertions.assertArrayEquals(expected, result);
        }
    }
}