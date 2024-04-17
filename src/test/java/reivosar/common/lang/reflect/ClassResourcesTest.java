package reivosar.common.lang.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ClassResourcesTest {
    
    @Nested
    class OfMethod {
        @Test
        void shouldReturnClassDescriptorIfExists() {
            ClassDescriptor descriptor = ClassResources.of(ClassResourcesTest.class);
            assertNotNull(descriptor);
        }
    }
    
    @Nested
    class OfStringMethod {
        
        @Test
        void shouldReturnClassDescriptorIfExists() {
            ClassDescriptor descriptor = ClassResources.of("reivosar.common.lang.reflect.ClassResourcesTest");
            assertNotNull(descriptor);
        }
        
        @Test
        void shouldReturnNullIfNotFound() {
            ClassDescriptor descriptor = ClassResources.of("com.example.NonExistentClass");
            assertNull(descriptor);
        }
    }
}