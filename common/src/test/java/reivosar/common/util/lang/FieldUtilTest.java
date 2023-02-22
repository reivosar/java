package reivosar.common.util.lang;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class FieldUtilTest {
    
    @Nested
    class ReadFieldTests {
        
        static class TestClass {
            String message = "ReadFieldTests";
        }
        
        @Test
        void shouldThrowIllegalStateExceptionIfFieldIsNull() {
            // given
            Field field = null;
            Object target = new Object();
            
            // when, then
            assertThrows(IllegalStateException.class, () -> FieldUtil.readField(field, target));
        }
        
        @Test
        void shouldThrowIllegalStateExceptionIfTargetIsNull() throws NoSuchFieldException {
            // given
            Field field = TestClass.class.getDeclaredField("message");
            Object target = null;
            
            // when, then
            assertThrows(IllegalStateException.class, () -> FieldUtil.readField(field, target));
        }
        
        @Test
        void shouldReturnValueOfField() throws NoSuchFieldException {
            // given
            Field field = TestClass.class.getDeclaredField("message");
            field.setAccessible(true);
            TestClass target = new TestClass();
            
            // when
            Object result = FieldUtil.readField(field, target);
            
            // then
            assertNotNull(result);
        }
    }
    
    @Nested
    class ReadStaticFieldTests {
        
        @Test
        void shouldThrowIllegalStateExceptionIfFieldIsNull() {
            // given
            Field field = null;
            
            // when, then
            assertThrows(IllegalStateException.class, () -> FieldUtil.readStaticField(field));
        }
        
        @Test
        void shouldReturnValueOfStaticField() throws NoSuchFieldException {
            // given
            Field field = Integer.class.getDeclaredField("MAX_VALUE");
            
            // when
            Object result = FieldUtil.readStaticField(field);
            
            // then
            assertNotNull(result);
        }
    }
    
    @Nested
    class WriteFieldTests {
        @Test
        void shouldSetFieldOnObject() throws NoSuchFieldException {
            // given
            MyClass obj = new MyClass();
            Field field = MyClass.class.getDeclaredField("myField");
            field.setAccessible(true);
            int expectedValue = 42;
            
            // when
            FieldUtil.writeField(field, obj, expectedValue);
            
            // then
            assertEquals(expectedValue, obj.myField);
        }
        
        @Test
        void shouldThrowIllegalStateExceptionIfObjectIsNull() throws NoSuchFieldException {
            // given
            Field field = MyClass.class.getDeclaredField("myField");
            int value = 42;
            
            // when
            Executable action = () -> FieldUtil.writeField(field, null, value);
            
            // then
            assertThrows(IllegalStateException.class, action);
        }
        
        @Test
        void shouldThrowIllegalStateExceptionIfFieldCannotBeAccessed() {
            // given
            Field field = null;
            int value = 42;
            
            // when
            Executable action = () -> FieldUtil.writeField(field, new MyClass(), value);
            
            // then
            assertThrows(IllegalStateException.class, action);
        }
    }
    
    @Nested
    class WriteStaticFieldTests {
        @Test
        void shouldSetStaticField() throws NoSuchFieldException {
            // given
            Field field = MyClass.class.getDeclaredField("myStaticField");
            field.setAccessible(true);
            int expectedValue = 42;
            
            // when
            FieldUtil.writeStaticField(field, expectedValue);
            
            // then
            assertEquals(expectedValue, MyClass.myStaticField);
        }
        
        @Test
        void shouldThrowIllegalStateExceptionIfFieldCannotBeAccessed() {
            // given
            Field field = null;
            int value = 42;
            
            // when
            Executable action = () -> FieldUtil.writeStaticField(field, value);
            
            // then
            assertThrows(IllegalStateException.class, action);
        }
    }
    
    private static class MyClass {
        private int myField;
        private static int myStaticField;
    }
}