package reivosar.common.util.event;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import reivosar.common.util.collection.CollectionUtil;
import reivosar.common.util.reflect.ClassDescriptor;
import reivosar.common.util.reflect.ClassDescriptorFactory;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class EventHandlerClassResourcesTest {
    
    @Nested
    class FindByEventTest {
        
        @ParameterizedTest
        @MethodSource("source")
        void shouldBeReturnExpectedValues(final Event event, Collection<ClassDescriptor> expected) {
            final Collection<ClassDescriptor> actual = EventHandlerClassResources.findByEvent(event);
            assertTrue(CollectionUtil.isEqualCollection(expected, actual));
        }
    
        public static Stream<Arguments> source() {
            return Stream.of(
                    arguments(new NotCalledEvent(), List.of()),
                    arguments(new ParentEvent(), getExpected(TestEventHandler1.class, TestEventHandler3.class)),
                    arguments(new ChildEvent(), getExpected(TestEventHandler4.class)),
                    arguments(new GrandChildEvent(), getExpected(TestEventHandler2.class, TestEventHandler5.class))
            );
        }
    
        private static List<ClassDescriptor> getExpected(Class<?>... classes) {
            return Arrays.stream(classes).map(ClassDescriptorFactory::create).toList();
        }
    
        static class ParentEvent implements Event {
        }
    
        static class ChildEvent extends ParentEvent {
        }
    
        static class GrandChildEvent extends ParentEvent {
        }
    
        record NotCalledEvent() implements Event {
        }
        
        static class TestEventHandler1 {
            void handle(final ParentEvent event) {
            }
        }
        
        static class TestEventHandler2 {
            void handle(final GrandChildEvent event) {
            }
        }
        
        static class TestEventHandler3 {
            void handle(final ParentEvent event) {
            }
        }
        
        static class TestEventHandler4 {
            void handle(final ChildEvent event) {
            }
        }
        
        static class TestEventHandler5 {
            void handle(final GrandChildEvent event) {
            }
        }
    }

}