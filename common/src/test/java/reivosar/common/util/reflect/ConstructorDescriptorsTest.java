package reivosar.common.util.reflect;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;

class ConstructorDescriptorsTest {
    
    @Nested
    class ConstructorTests {
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenNullPassed() {
            // given
            Constructor<?>[] nullArray = null;
            // when
            ConstructorMetadataDescriptors descriptors = new ConstructorMetadataDescriptors(nullArray);
            // then
            Assertions.assertTrue(descriptors.descriptors().isEmpty());
        }
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenEmptyArrayPassed() {
            // given
            Constructor<?>[] emptyArray = new Constructor[0];
            // when
            ConstructorMetadataDescriptors descriptors = new ConstructorMetadataDescriptors(emptyArray);
            // then
            Assertions.assertTrue(descriptors.descriptors().isEmpty());
        }
        
        @Test
        void shouldCreateNonEmptyDescriptorsListWhenDefaultConstructorClassObjectPassed() {
            // given
            Constructor<?>[] Constructors = DefaultConstructorClass.class.getDeclaredConstructors();
            // when
            ConstructorMetadataDescriptors descriptors = new ConstructorMetadataDescriptors(Constructors);
            // then
            Assertions.assertEquals(1, descriptors.descriptors().size());
        }
        
        @Test
        void shouldCreateNonEmptyDescriptorsListWhenTwoConstructorsClassObjectPassed() {
            // given
            Constructor<?>[] Constructors = TwoConstructorClass.class.getDeclaredConstructors();
            // when
            ConstructorMetadataDescriptors descriptors = new ConstructorMetadataDescriptors(Constructors);
            // then
            Assertions.assertEquals(2, descriptors.descriptors().size());
        }
    
        private class DefaultConstructorClass {
        
        }
        
        private class TwoConstructorClass {
            
            private final String str;
    
            public TwoConstructorClass(final String str) {
                this.str = str;
            }
    
            public TwoConstructorClass(final Boolean bool) {
                this(bool.toString());
            }
        }
    }
}