package reivosar.common.util.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClassDescriptorsTest {
    
    @Nested
    class OfMethod {
        @Test
        void shouldReturnClassDescriptorIfExists() {
            ClassDescriptor descriptor = ClassDescriptors.of(ClassDescriptorsTest.class);
            assertNotNull(descriptor);
            assertEquals(ClassDescriptorsTest.class.getSimpleName(), descriptor.getClassName());
        }
    }
    
    @Nested
    class OfStringMethod {
        
        @Test
        void shouldReturnClassDescriptorIfExists() {
            ClassDescriptor descriptor = ClassDescriptors.of("reivosar.common.util.reflect.ClassDescriptorsTest");
            assertNotNull(descriptor);
            assertEquals(ClassDescriptorsTest.class.getSimpleName(), descriptor.getClassName());
        }
        
        @Test
        void shouldReturnNullIfNotFound() {
            ClassDescriptor descriptor = ClassDescriptors.of("com.example.NonExistentClass");
            assertNull(descriptor);
        }
    }

}