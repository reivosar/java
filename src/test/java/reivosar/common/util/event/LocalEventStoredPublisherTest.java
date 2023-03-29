package reivosar.common.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.collection.CollectionUtil;
import reivosar.common.util.promise.Promise;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalEventStoredPublisherTest {
    
    @Nested
    class OneEventOneHandlerTest {
        
        private LocalEventStoredPublisher testClass;
    
        private static final List<String> eventResults = Collections.synchronizedList(new ArrayList<>());
        
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventStoredPublisher();
        }
        
        @Test
        void shouldReturnTrueWhenPassedExecutableEvent() throws InterruptedException {
            // given
            final SimpleTestEvent testEvent = new SimpleTestEvent(LocalDateTime.now());
            // when
            final Promise<Void> result = this.testClass.publish(testEvent);
            // then
            assertTrue(result.success());
            while (eventResults.isEmpty()) {
                Thread.sleep(1000);
            }
            assertTrue(CollectionUtil.isEqualCollection(eventResults,
                    List.of("TestEventHandler")));
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
    class ManyEvenManyHandlerTest {
        
        private LocalEventStoredPublisher testClass;
        
        private static final List<String> eventResults = Collections.synchronizedList(new ArrayList<>());
        
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventStoredPublisher();
        }
        
        @Test
        void shouldReturnTrueWhenPassedExecutableEvents() throws InterruptedException {
            // given
            final Collection<Event> testEvents = Arrays.asList(new ParentEvent(), new ChildEvent());
            // when
            final Promise<Void> result = this.testClass.publish(testEvents);
            // then
            assertTrue(result.success());
            while (eventResults.size() != 4) {
                Thread.sleep(1000);
            }
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
            void handle(final LocalDispatchEventPublisherTest.ManyEvenManyHandlerTest.NotCalledEvent event) {
                eventResults.add("TestEventHandler5");
            }
        }
    }
}