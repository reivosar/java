package reivosar.common.util.lang;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FieldUtilTest {
    
    @Nested
    class GetAllFieldsTests {
        
        private Class<MyClass> testClassClass;
        
        @BeforeEach
        void setUp() {
            testClassClass = MyClass.class;
        }
        
        @Test
        void shouldReturnAllFieldsInClass() {
            Collection<Field> fields = FieldUtil.getAllFields(testClassClass);
            assertEquals(9, fields.size());
        }
        
        @Test
        void shouldReturnEmptyCollectionForInterface() {
            Collection<Field> fields = FieldUtil.getAllFields(Set.class);
            assertTrue(fields.isEmpty());
        }
        
    }
    
    @Nested
    class GetAllFieldArrayTests {
        
        private Class<MyClass> testClassClass;
        
        @BeforeEach
        void setUp() {
            testClassClass = MyClass.class;
        }
        
        @Test
        void shouldReturnAllFieldsInClassAsArray() {
            Field[] fields = FieldUtil.getAllFieldArray(testClassClass);
            assertEquals(9, fields.length);
        }
        
        @Test
        void shouldReturnEmptyArrayForInterface() {
            Field[] fields = FieldUtil.getAllFieldArray(Set.class);
            assertEquals(0, fields.length);
        }
    }
    
    @Nested
    class GetDeclaredFieldTests {
        
        private Class<MyClass> testClassClass;
        
        @BeforeEach
        void setUp() {
            testClassClass = MyClass.class;
        }
        
        @Test
        void shouldReturnFieldInClass() {
            Field field = FieldUtil.getDeclaredField(testClassClass, "publicField");
            assertNotNull(field);
            assertEquals("publicField", field.getName());
        }
        
        @Test
        void shouldReturnNullForNonExistingField() {
            Field field = FieldUtil.getDeclaredField(testClassClass, "nonExistingField");
            assertNull(field);
        }
        
        @Test
        void shouldReturnNullForFieldInSuperclass() {
            Field field = FieldUtil.getDeclaredField(testClassClass, "superclassField");
            assertNull(field);
        }
        
        @Test
        void shouldReturnPrivateFieldInSuperclass() {
            Field field = FieldUtil.getDeclaredField(testClassClass, "privateField");
            assertNotNull(field);
            assertEquals("privateField", field.getName());
        }
        
        @Test
        void shouldReturnPrivateFieldInClass() {
            Field field = FieldUtil.getDeclaredField(testClassClass, "privateClassField");
            assertNotNull(field);
            assertEquals("privateClassField", field.getName());
        }
        
    }
    
    @Nested
    class ReadFieldTests {
        
        static class TestClass {
            String message = "ReadFieldTests";
        }
        
        @Test
        void shouldThrowNullPointerExceptionIfFieldIsNull() {
            // given
            Field field = null;
            Object target = new Object();
            
            // when, then
            assertThrows(NullPointerException.class, () -> FieldUtil.readField(field, target));
        }
        
        @Test
        void shouldThrowNullPointerExceptionIfTargetIsNull() throws NoSuchFieldException {
            // given
            Field field = TestClass.class.getDeclaredField("message");
            Object target = null;
            
            // when, then
            assertThrows(NullPointerException.class, () -> FieldUtil.readField(field, target));
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
        void shouldThrowNullPointerExceptionIfFieldIsNull() {
            // given
            Field field = null;
            
            // when, then
            assertThrows(NullPointerException.class, () -> FieldUtil.readStaticField(field));
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
        void shouldThrowNullPointerExceptionIfObjectIsNull() throws NoSuchFieldException {
            // given
            Field field = MyClass.class.getDeclaredField("myField");
            int value = 42;
            
            // when
            Executable action = () -> FieldUtil.writeField(field, null, value);
            
            // then
            assertThrows(NullPointerException.class, action);
        }
        
        @Test
        void shouldThrowNullPointerExceptionIfFieldCannotBeAccessed() {
            // given
            Field field = null;
            int value = 42;
            
            // when
            Executable action = () -> FieldUtil.writeField(field, new MyClass(), value);
            
            // then
            assertThrows(NullPointerException.class, action);
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
        void shouldThrowNullPointerExceptionIfFieldCannotBeAccessed() {
            // given
            Field field = null;
            int value = 42;
            
            // when
            Executable action = () -> FieldUtil.writeStaticField(field, value);
            
            // then
            assertThrows(NullPointerException.class, action);
        }
    }
    
    private static class Superclass {
        private String superclassField;
    }
    
    private static class MyClass extends Superclass {
        private int myField;
        private static int myStaticField;
    
        public int publicField;
        protected String protectedField;
        private boolean privateField;
        private static List<String> privateClassField;
    
    }
}