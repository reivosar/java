package reivosar.common.lang.reflect.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

class   MethodDescriptorsTest {
    
    @Nested
    class ConstructorTests {
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenNullPassed() {
            // given
            Method[] nullArray = null;
            // when
            MethodDescriptors descriptors = MethodDescriptorsFactory.createDescriptors(nullArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenEmptyArrayPassed() {
            // given
            Method[] emptyArray = new Method[0];
            // when
            MethodDescriptors descriptors = MethodDescriptorsFactory.createDescriptors(emptyArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateNonEmptyDescriptorsList() {
            // given
            Method[] methods = getClass().getDeclaredMethods();
            // when
            MethodDescriptors descriptors = MethodDescriptorsFactory.createDescriptors(getClass().getDeclaredMethods());
            // then
            Assertions.assertEquals(3, descriptors.getDescriptors().size());
        }
    }
}