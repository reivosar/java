package reivosar.common.async.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.async.promise.Promise;
import reivosar.common.data.collection.CollectionUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalStoredEventPublisherTest {

    @Nested
    class OneEventOneHandlerTest {

        private LocalStoredEventPublisher<Event> testClass;

        private static final List<String> eventResults = Collections.synchronizedList(new ArrayList<>());

        @BeforeEach
        void setup() {
            this.testClass = new LocalStoredEventPublisher<>();
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
                Thread.sleep(300);
            }
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
    class ManyEventManyHandlerTest {

        private LocalStoredEventPublisher<Event> testClass;

        private static final List<String> eventResults = Collections.synchronizedList(new ArrayList<>());

        @BeforeEach
        void setup() {
            this.testClass = new LocalStoredEventPublisher<>();
        }

        @Test
        void shouldReturnTrueWhenPassedExecutableEvents() throws InterruptedException {
            // given
            final Collection<Event> testEvents = Arrays.asList(new ParentEvent(), new ChildEvent());
            final GrandChildEvent testEvent = new GrandChildEvent();
            // when
            final Promise<Void> result1 = this.testClass.publish(testEvents);
            final Promise<Void> result2 = this.testClass.publish(testEvent);
            // then
            assertTrue(result1.success() && result2.success());
            while (eventResults.size() != 5) {
                Thread.sleep(300);
            }
            assertTrue(CollectionUtil.isEqualCollection(eventResults,
                    Arrays.asList("TestEventHandler1", "TestEventHandler2", "TestEventHandler3", "TestEventHandler4", "TestEventHandler6")));
        }

        class ParentEvent implements Event {
        }

        class ChildEvent extends ParentEvent {
        }

        class GrandChildEvent extends ChildEvent {
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

        static class TestEventHandler6 {
            void handle(final ChildEvent event) {
                eventResults.add("TestEventHandler6");
            }
        }
    }

    @Nested
    class TooManyEventTest {

        private LocalStoredEventPublisher<Event> testClass;

        private static final List<Integer> eventResults = Collections.synchronizedList(new ArrayList<>());

        private static final int MAX_EVENT_SIZE = 512;

        @BeforeEach
        void setup() {
            this.testClass = new LocalStoredEventPublisher<>();
        }

        @Test
        void shouldReturnTrueWhenPassedExecutableEvents() throws InterruptedException {
            // given
            final Collection<Event> testEvents = IntStream.range(0, MAX_EVENT_SIZE).mapToObj(TestEvent::new).collect(Collectors.toList());
            // when
            final Promise<Void> result = this.testClass.publish(testEvents);
            // then
            assertTrue(result.success());
            while (eventResults.size() != MAX_EVENT_SIZE) {
                System.out.println(eventResults.size());
                Thread.sleep(300);
            }
            assertEquals(eventResults.size(), MAX_EVENT_SIZE);
        }

        record TestEvent(int value) implements Event {
        }

        static class TestEventHandler {
            void handle(final TestEvent event) {
                eventResults.add(event.value());
            }
        }
    }
}