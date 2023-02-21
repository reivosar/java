package reivosar.common.util.reflect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

class MethodDescriptorsTest {
    
    @Nested
    class ConstructorTests {
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenNullPassed() {
            // given
            Method[] nullArray = null;
            // when
            MethodDescriptors descriptors = new MethodDescriptors(nullArray);
            // then
            Assertions.assertTrue(descriptors.descriptors().isEmpty());
        }
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenEmptyArrayPassed() {
            // given
            Method[] emptyArray = new Method[0];
            // when
            MethodDescriptors descriptors = new MethodDescriptors(emptyArray);
            // then
            Assertions.assertTrue(descriptors.descriptors().isEmpty());
        }
        
        @Test
        void shouldCreateNonEmptyDescriptorsList() {
            // given
            Method[] methods = getClass().getDeclaredMethods();
            // when
            MethodDescriptors descriptors = new MethodDescriptors(getClass().getDeclaredMethods());
            // then
            Assertions.assertEquals(3, descriptors.descriptors().size());
        }
    }
}