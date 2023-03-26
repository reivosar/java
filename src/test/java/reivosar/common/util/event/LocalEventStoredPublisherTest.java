package reivosar.common.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.promise.Promise;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalEventStoredPublisherTest {
    
    @Nested
    class OneEventOneHandlerTest {
        
        private LocalEventStoredPublisher testClass;
        
        @BeforeEach
        void setup() {
            this.testClass = new LocalEventStoredPublisher();
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
                System.out.println("TestEventHandler1");
                System.out.println(event.occurredOn());
            }
        }
    }
}