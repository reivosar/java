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
            MethodMetadataDescriptors descriptors = new MethodMetadataDescriptors(nullArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenEmptyArrayPassed() {
            // given
            Method[] emptyArray = new Method[0];
            // when
            MethodMetadataDescriptors descriptors = new MethodMetadataDescriptors(emptyArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateNonEmptyDescriptorsList() {
            // given
            Method[] methods = getClass().getDeclaredMethods();
            // when
            MethodMetadataDescriptors descriptors = new MethodMetadataDescriptors(getClass().getDeclaredMethods());
            // then
            Assertions.assertEquals(3, descriptors.getDescriptors().size());
        }
    }
}