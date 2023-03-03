package reivosar.common.util.reflect.member;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.reflect.member.ConstructorDescriptors;
import reivosar.common.util.reflect.member.ConstructorDescriptorsFactory;

import java.lang.reflect.Constructor;

class ConstructorDescriptorsTest {
    
    @Nested
    class ConstructorTests {
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenNullPassed() {
            // given
            Constructor<?>[] nullArray = null;
            // when
            ConstructorDescriptors descriptors = ConstructorDescriptorsFactory.createDescriptors(nullArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateEmptyDescriptorsListWhenEmptyArrayPassed() {
            // given
            Constructor<?>[] emptyArray = new Constructor[0];
            // when
            ConstructorDescriptors descriptors = ConstructorDescriptorsFactory.createDescriptors(emptyArray);
            // then
            Assertions.assertTrue(descriptors.getDescriptors().isEmpty());
        }
        
        @Test
        void shouldCreateNonEmptyDescriptorsListWhenDefaultConstructorClassObjectPassed() {
            // given
            Constructor<?>[] Constructors = DefaultConstructorClass.class.getDeclaredConstructors();
            // when
            ConstructorDescriptors descriptors = ConstructorDescriptorsFactory.createDescriptors(Constructors);
            // then
            Assertions.assertEquals(1, descriptors.getDescriptors().size());
        }
        
        @Test
        void shouldCreateNonEmptyDescriptorsListWhenTwoConstructorsClassObjectPassed() {
            // given
            Constructor<?>[] Constructors = TwoConstructorClass.class.getDeclaredConstructors();
            // when
            ConstructorDescriptors descriptors = ConstructorDescriptorsFactory.createDescriptors(Constructors);
            // then
            Assertions.assertEquals(2, descriptors.getDescriptors().size());
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