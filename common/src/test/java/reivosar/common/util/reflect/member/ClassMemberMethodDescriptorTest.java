package reivosar.common.util.reflect.member;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ClassMemberMethodDescriptorTest {
    
    @Nested
    class InvokeMethodTests {
        
        @Test
        void shouldInvokeMethod() {
            Method method = getMethod("sum", int.class, int.class);
            MethodDescriptor descriptor = new ClassMemberMethodDescriptor(method);
            
            int result = (int) descriptor.getMethodAccessor().invokeMethod(new Calculator(), 2, 3);
            
            assertEquals(5, result);
        }
        
        @Test
        void shouldThrowExceptionIfTargetIsNull() {
            Method method = getMethod("sum", int.class, int.class);
            MethodDescriptor descriptor = new ClassMemberMethodDescriptor(method);
            
            assertThrows(NullPointerException.class, () -> descriptor.getMethodAccessor().invokeMethod(null, 2, 3));
        }
        
        @Test
        void shouldThrowExceptionIfParameterTypesAreNotMatched() {
            Method method = getMethod("sum", int.class, int.class);
            MethodDescriptor descriptor = new ClassMemberMethodDescriptor(method);
            
            assertThrows(IllegalStateException.class, () -> descriptor.getMethodAccessor().invokeMethod(new Calculator(), 2, "3"));
        }
        
        private Method getMethod(String name, Class<?>... parameterTypes) {
            try {
                return Calculator.class.getDeclaredMethod(name, parameterTypes);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    @Nested
    class InvokeStaticMethodTests {
        
        @Test
        void shouldInvokeStaticMethod() {
            Method method = getMethod("subtract", int.class, int.class);
            MethodDescriptor descriptor = new ClassMemberMethodDescriptor(method);
            
            int result = (int) descriptor.getMethodAccessor().invokeStaticMethod(5, 2);
            
            assertEquals(3, result);
        }
        
        @Test
        void shouldThrowExceptionIfParameterTypesAreNotMatched() {
            Method method = getMethod("subtract", int.class, int.class);
            MethodDescriptor descriptor = new ClassMemberMethodDescriptor(method);
            
            assertThrows(IllegalStateException.class, () -> descriptor.getMethodAccessor().invokeStaticMethod(5, "2"));
        }
        
        private Method getMethod(String name, Class<?>... parameterTypes) {
            try {
                return Calculator.class.getDeclaredMethod(name, parameterTypes);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    static class Calculator {
        int sum(int a, int b) {
            return a + b;
        }
        
        static int subtract(int a, int b) {
            return a - b;
        }
    }
}