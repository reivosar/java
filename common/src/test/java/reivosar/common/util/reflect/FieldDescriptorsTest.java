package reivosar.common.util.reflect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldDescriptorsTest {
    
    @Nested
    class ConstructorTests {
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenNullPassed() {
            // given
            Field[] nullArray = null;
            // when
            FieldDescriptors descriptors = new FieldDescriptors(nullArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenEmptyArrayPassed() {
            // given
            Field[] emptyArray = new Field[0];
            // when
            FieldDescriptors descriptors = new FieldDescriptors(emptyArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateNonEmptyDescriptorsList() {
            // given
            Field[] fields = TestClass.class.getDeclaredFields();
            // when
            FieldDescriptors descriptors = new FieldDescriptors(fields);
            // then
            assertTrue(descriptors.getDescriptors().size() > 0);
            assertTrue(descriptors.getMemberNames().contains("name"));
            assertTrue(descriptors.getMemberNames().contains("this$1"));
            assertTrue(descriptors.getMemberNames().contains("result"));
        }
        
        private class TestClass {
            public String name;
            protected int this$1;
            private boolean result;
        }
    }
}