package reivosar.common.async.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.async.promise.Promise;
import reivosar.common.data.collection.CollectionUtil;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalDispatchEventPublisherTest {
    
    @Nested
    class NotExecuteEventTest {

        private LocalEventPublisher<Event> testClass;
        
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventPublisher<>();
        }
        
        @Test
        void shouldReturnTrueWhenNoDefaultConstructorClass() {
            // given
            final NoDefaultConstructorEvent testEvent = new NoDefaultConstructorEvent(LocalDateTime.now());
            // when
            final Promise<Void> result = this.testClass.publish(testEvent);
            // then
            assertTrue(result.success());
        }
        
        @Test
        void shouldReturnTrueWhenNoClassFoundToHandleTheEvent() {
            // given
            final NoEventMethodEvent testEvent = new NoEventMethodEvent(LocalDateTime.now());
            // when
            final Promise<Void> result = this.testClass.publish(testEvent);
            // then
            assertTrue(result.success());
        }
        
        record NoEventMethodEvent(LocalDateTime occurredOn) implements Event {
        }
        
        record NoDefaultConstructorEvent(LocalDateTime occurredOn) implements Event {
        }
        
        static class NoDefaultConstructorEventHandler {
            private final int number;
            
            NoDefaultConstructorEventHandler(final int number) {
                this.number = number;
            }
            
            void handle(final NoDefaultConstructorEvent event) {
                System.out.println(event.occurredOn());
            }
        }
        
        static class NoEventMethodHandler {
            void handle(final String event) {
                System.out.println(event);
            }
        }
    }
    
    @Nested
    class OneEventOneHandlerTest {

        private LocalEventPublisher<Event> testClass;
        
        private static final List<String> eventResults = Collections.synchronizedList(new ArrayList<>());
        
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventPublisher<>();
        }
        
        @Test
        void shouldReturnTrueWhenPassedExecutableEvent() {
            // given
            final SimpleTestEvent testEvent = new SimpleTestEvent(LocalDateTime.now());
            // when
            final Promise<Void> result = this.testClass.publish(testEvent);
            result.throwIfError();
            // then
            assertTrue(result.success());
            assertTrue(CollectionUtil.isEqualCollection(eventResults, List.of("TestEventHandler")));
        }
        
        record SimpleTestEvent(LocalDateTime occurredOn) implements Event {
        }
        
        static class TestEventHandler {
            void handle(final SimpleTestEvent event) {
                eventResults.add("TestEventHandler");
            }
        }
    }
    
    @Nested
    class OneEventTwoHandlerTest {

        private LocalEventPublisher<Event> testClass;
        
        private static final List<String> eventResults = Collections.synchronizedList(new ArrayList<>());
        
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventPublisher<>();
        }
        
        @Test
        void shouldReturnTrueWhenPassedExecutableEvents() {
            // given
            final ManyTestEvent testEvent = new ManyTestEvent(LocalDateTime.now());
            // when
            final Promise<Void> result = this.testClass.publish(testEvent);
            // then
            assertTrue(result.success());
            assertTrue(CollectionUtil.isEqualCollection(eventResults,
                    Arrays.asList("TestEventHandler1", "TestEventHandler2")));
        }
        
        record ManyTestEvent(LocalDateTime occurredOn) implements Event {
        }
        
        static class TestEventHandler1 {
            void handle(final ManyTestEvent event) {
                eventResults.add("TestEventHandler1");
            }
        }
        
        static class TestEventHandler2 {
            void handle(final ManyTestEvent event) {
                eventResults.add("TestEventHandler2");
            }
        }
    }
    
    @Nested
    class ManyEventManyHandlerTest {

        private LocalEventPublisher<Event> testClass;
        
        private static final List<String> eventResults = Collections.synchronizedList(new ArrayList<>());
        
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventPublisher<>();
        }
        
        @Test
        void shouldReturnTrueWhenPassedExecutableEvents() {
            // given
            final Collection<Event> testEvents = Arrays.asList(new ParentEvent(), new ChildEvent());
            // when
            final Promise<Void> result = this.testClass.publish(testEvents);
            // then
            assertTrue(result.success());
            System.out.println(eventResults);
            assertTrue(CollectionUtil.isEqualCollection(eventResults,
                    Arrays.asList("TestEventHandler1", "TestEventHandler2", "TestEventHandler3", "TestEventHandler4")));
        }
        
        class ParentEvent implements Event {
        }
        
        class ChildEvent extends ParentEvent {
        }
        
        record NotCalledEvent() implements Event {
        }
        
        static class TestEventHandler1 {
            void handle(final ParentEvent event) {
                eventResults.add("TestEventHandler1");
            }
        }
        
        static class TestEventHandler2 {
            void handle(final ChildEvent event) {
                eventResults.add("TestEventHandler2");
            }
        }
        
        static class TestEventHandler3 {
            void handle(final ParentEvent event) {
                eventResults.add("TestEventHandler3");
            }
        }
        
        static class TestEventHandler4 {
            void handle(final ChildEvent event) {
                eventResults.add("TestEventHandler4");
            }
        }
        
        static class TestEventHandler5 {
            void handle(final NotCalledEvent event) {
                eventResults.add("TestEventHandler5");
            }
        }
    }
}