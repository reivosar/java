package reivosar.common.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.collection.CollectionUtil;
import reivosar.common.util.promise.Promise;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class LocalEventParameterPublisherTest {
    
    @Nested
    class NotExecuteEventTest {
    
        private LocalEventPublisher testClass;
    
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventPublisher();
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
    
        private LocalEventPublisher testClass;
    
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventPublisher();
        }
    
        @Test
        void shouldReturnTrueWhenPassedExecutableEvent() {
            // given
            final SimpleTestEvent testEvent = new SimpleTestEvent(LocalDateTime.now());
            // when
            final Promise<Void> result = this.testClass.publish(testEvent);
            // then
            assertTrue(result.success());
        }
    
        record SimpleTestEvent(LocalDateTime occurredOn) implements Event {
        }
    
        static class TestEventHandler {
            void handle(final SimpleTestEvent event) {
                System.out.println("TestEventHandler2");
                System.out.println(event.occurredOn());
            }
        }
    }
    
    @Nested
    class OneEvenTwoHandlerTest {
        
        private LocalEventPublisher testClass;
    
        private static List<String> eventResults = Collections.synchronizedList(new ArrayList<>());
        
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventPublisher();
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
                    Arrays.asList("TestEventHandler1","TestEventHandler2")));
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
    class ManyEvenManyHandlerTest {
        
        private LocalEventPublisher testClass;
        
        private static List<String> eventResults = Collections.synchronizedList(new ArrayList<>());
        
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventPublisher();
        }
        
        @Test
        void shouldReturnTrueWhenPassedExecutableEvents() {
            // given
            final Collection<Event> testEvents = Arrays.asList(new ManyTestEvent1(LocalDateTime.now()), new ManyTestEvent2(LocalDateTime.now()));
            // when
            final Promise<Void> result = this.testClass.publish(testEvents);
            // then
            assertTrue(result.success());
            assertTrue(CollectionUtil.isEqualCollection(eventResults,
                    Arrays.asList("TestEventHandler1","TestEventHandler2","TestEventHandler3","TestEventHandler4")));
        }
        
        record ManyTestEvent1(LocalDateTime occurredOn) implements Event {
        }
        record ManyTestEvent2(LocalDateTime occurredOn) implements Event {
        }
        record NotCalledEvent(LocalDateTime occurredOn) implements Event {
        }
        
        static class TestEventHandler1 {
            void handle(final ManyTestEvent1 event) {
                eventResults.add("TestEventHandler1");
            }
        }
        
        static class TestEventHandler2 {
            void handle(final ManyTestEvent2 event) {
                eventResults.add("TestEventHandler2");
            }
        }
    
        static class TestEventHandler3 {
            void handle(final ManyTestEvent1 event) {
                eventResults.add("TestEventHandler3");
            }
        }
    
        static class TestEventHandler4 {
            void handle(final ManyTestEvent2 event) {
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