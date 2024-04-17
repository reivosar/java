package reivosar.common.lang;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class MethodUtilTest {
    
    @Nested
    class InvokeMethodTests {
        
        private final String testString = "hello world";
        
        @Test
        void shouldInvokeMethodAndReturnResult() throws NoSuchMethodException {
            // Given
            final Method method = String.class.getMethod("length");
            
            // When
            final Object result = MethodUtil.invokeMethod(method, testString);
            
            // Then
            assertNotNull(result);
            assertEquals(11, result);
        }
        
        @Test
        void shouldThrowIllegalStateExceptionIfMethodInvocationThrowsException() throws NoSuchMethodException {
            // Given
            final Method method = String.class.getMethod("charAt", int.class);
            final int index = testString.length();
            
            // When
            final Throwable exception = assertThrows(IllegalStateException.class, () -> MethodUtil.invokeMethod(method, testString, index));
            
            // Then
            assertNotNull(exception.getCause());
            assertTrue(exception.getCause() instanceof InvocationTargetException);
        }
    }
    
    @Nested
    class InvokeStaticMethodTests {
        
        @Test
        void shouldInvokeStaticMethodAndReturnResult() throws NoSuchMethodException {
            // Given
            final Method method = Integer.class.getMethod("valueOf", String.class);
            final String input = "123";
            
            // When
            final Object result = MethodUtil.invokeStaticMethod(method, input);
            
            // Then
            assertNotNull(result);
            assertEquals(123, result);
        }
        
        @Test
        void shouldThrowIllegalStateExceptionIfMethodInvocationThrowsException() throws NoSuchMethodException {
            // Given
            final Method method = Integer.class.getMethod("valueOf", String.class);
            final String input = "not a number";
            
            // When
            final Throwable exception = assertThrows(IllegalStateException.class, () -> MethodUtil.invokeStaticMethod(method, input));
            
            // Then
            assertNotNull(exception.getCause());
            assertTrue(exception.getCause() instanceof InvocationTargetException);
        }
    }
}