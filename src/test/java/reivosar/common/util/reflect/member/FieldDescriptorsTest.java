package reivosar.common.util.reflect.member;

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
            FieldDescriptors descriptors = FieldDescriptorsFactory.createDescriptors(nullArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenEmptyArrayPassed() {
            // given
            Field[] emptyArray = new Field[0];
            // when
            FieldDescriptors descriptors = FieldDescriptorsFactory.createDescriptors(emptyArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateNonEmptyDescriptorsList() {
            // given
            Field[] fields = TestClass.class.getDeclaredFields();
            // when
            FieldDescriptors descriptors = FieldDescriptorsFactory.createDescriptors(fields);
            // then
            assertTrue(descriptors.getDescriptors().size() > 0);
            assertTrue(descriptors.getNames().contains("name"));
            assertTrue(descriptors.getNames().contains("this$1"));
            assertTrue(descriptors.getNames().contains("result"));
        }
        
        private class TestClass {
            public String name;
            protected int this$1;
            private boolean result;
        }
    }
}