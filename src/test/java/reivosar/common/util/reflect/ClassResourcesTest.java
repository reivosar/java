package reivosar.common.util.reflect;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.event.Event;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
            ClassDescriptor descriptor = ClassResources.of("reivosar.common.util.reflect.ClassResourcesTest");
            assertNotNull(descriptor);
        }
        
        @Test
        void shouldReturnNullIfNotFound() {
            ClassDescriptor descriptor = ClassResources.of("com.example.NonExistentClass");
            assertNull(descriptor);
        }
    }
    
    @Nested
    class FindByMethodParametersObjectMethod {
        @Test
        void shouldBeReturnClassDescriptorsIfExistsMethodWithOneParameterDefined() {
            // GIVEN
            final TestEvent event = new TestEvent();
            // WHEN
            final ClassDescriptors classDescriptors = ClassResources.findByMethodParameters(event);
            // THEN
            assertEquals(1, classDescriptors.getClassDescriptors().size());
            assertEquals(SingleParameterHandler.class.getName(),
                    classDescriptors.getClassDescriptors().stream().findFirst().get().getName());
        }
    
        @Test
        void shouldBeReturnClassDescriptorsIfExistsMethodWithParametersDefined() {
            // GIVEN
            final TestEvent event = new TestEvent();
            // WHEN
            final ClassDescriptors classDescriptors = ClassResources.findByMethodParameters(event, "String", 123);
            // THEN
            assertEquals(1, classDescriptors.getClassDescriptors().size());
            assertEquals(ManyParametersHandler.class.getName(),
                    classDescriptors.getClassDescriptors().stream().findFirst().get().getName());
        }
    
        @Test
        void shouldBeReturnEmptyClassDescriptorsIfNotExistsMethodWithParametersDefined() {
            // GIVEN
            final NoHandlerTestEvent event = new NoHandlerTestEvent();
            // WHEN
            final ClassDescriptors classDescriptors = ClassResources.findByMethodParameters(event);
            // THEN
            assertEquals(0, classDescriptors.getClassDescriptors().size());
        }
        
        record TestEvent() implements Event {
        }
        record NoHandlerTestEvent() implements Event {
        }
        static class SingleParameterHandler {
            void handle(TestEvent event) {
                // Do nothing
            }
        }
        static class ManyParametersHandler {
            void handle(TestEvent event, String str) {
                // Do nothing
            }
            void handle(TestEvent event, String str, int num) {
                // Do nothing
            }
            void handle(TestEvent event, String str, int num, List<Boolean> bool) {
                // Do nothing
            }
        }
    }
}