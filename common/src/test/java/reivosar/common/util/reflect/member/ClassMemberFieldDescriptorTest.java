package reivosar.common.util.reflect.member;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClassMemberFieldDescriptorTest {
    
    @Nested
    class GetProfileTests {
    
        private class TestClass {
            public int intField;
            private String stringField;
            protected List<Boolean> listOfBooleanField;
            Map<String, Integer> mapOfStringIntegerField;
            public CustomClass customClassField;
        }
    
        private record CustomClass() {
        }
        
        @Test
        void shouldReturnIntClassForIntField() throws NoSuchFieldException {
            Field field = TestClass.class.getDeclaredField("intField");
            FieldDescriptor fieldDescriptor = new ClassMemberFieldDescriptor(field);
            assertEquals("intField", fieldDescriptor.getName());
            assertTrue(fieldDescriptor.getClassModifier().isPublic());
        }
        
        @Test
        void shouldReturnStringClassForStringField() throws NoSuchFieldException {
            Field field = TestClass.class.getDeclaredField("stringField");
            FieldDescriptor fieldDescriptor = new ClassMemberFieldDescriptor(field);
            assertEquals("stringField", fieldDescriptor.getName());
            assertTrue(fieldDescriptor.getClassModifier().isPrivate());
        }
        
        @Test
        void shouldReturnListClassForListOfBooleanField() throws NoSuchFieldException {
            Field field = TestClass.class.getDeclaredField("listOfBooleanField");
            FieldDescriptor fieldDescriptor = new ClassMemberFieldDescriptor(field);
            assertEquals("listOfBooleanField", fieldDescriptor.getName());
            assertTrue(fieldDescriptor.getClassModifier().isProtected());
        }
        
        @Test
        void shouldReturnMapClassForMapOfStringIntegerField() throws NoSuchFieldException {
            Field field = TestClass.class.getDeclaredField("mapOfStringIntegerField");
            FieldDescriptor fieldDescriptor = new ClassMemberFieldDescriptor(field);
            assertEquals("mapOfStringIntegerField", fieldDescriptor.getName());
            assertTrue(fieldDescriptor.getClassModifier().isPackagePrivate());
        }
        
        @Test
        void shouldReturnCustomClassForCustomClassField() throws NoSuchFieldException {
            Field field = TestClass.class.getDeclaredField("customClassField");
            FieldDescriptor fieldDescriptor = new ClassMemberFieldDescriptor(field);
            assertEquals("customClassField", fieldDescriptor.getName());
            assertTrue(fieldDescriptor.getClassModifier().isPublic());
        }
    }
    
    @Nested
    class ReadFieldTests {
    
        private class Example {
            public String publicField;
            public static String STATIC_FIELD = "static value";
        }
    
        @Test
        void shouldReadStaticField() throws NoSuchFieldException {
            // given
            final FieldDescriptor fieldDescriptor = new ClassMemberFieldDescriptor(Example.class.getDeclaredField("STATIC_FIELD"));
            
            // when
            final Object fieldValue = fieldDescriptor.getFieldAccessor().readStaticField();
            
            // then
            assertEquals(Example.STATIC_FIELD, fieldValue);
        }
        
        @Test
        void shouldReadNonStaticField() throws NoSuchFieldException {
            // given
            final Example example = new Example();
            example.publicField = "hello";
            final FieldDescriptor fieldDescriptor = new ClassMemberFieldDescriptor(Example.class.getDeclaredField("publicField"));
            
            // when
            final Object fieldValue = fieldDescriptor.getFieldAccessor().readField(example);
            
            // then
            assertEquals(example.publicField, fieldValue);
        }
    }
    
    @Nested
    class SetFieldTests {
    
        private class Example {
            public String publicField;
            public static String STATIC_FIELD = "static value";
        }
        
        @Test
        void shouldSetStaticField() throws NoSuchFieldException {
            // given
            final FieldDescriptor fieldDescriptor = new ClassMemberFieldDescriptor(Example.class.getDeclaredField("STATIC_FIELD"));
            
            // when
            fieldDescriptor.getFieldAccessor().writeStaticField("new value");
            
            // then
            assertEquals("new value", Example.STATIC_FIELD);
        }
        
        @Test
        void shouldSetNonStaticField() throws NoSuchFieldException {
            // given
            final Example example = new Example();
            final FieldDescriptor fieldDescriptor = new ClassMemberFieldDescriptor(Example.class.getDeclaredField("publicField"));
            
            // when
            fieldDescriptor.getFieldAccessor().writeField(example, "new value");
            
            // then
            assertEquals("new value", example.publicField);
        }
    }
}