package reivosar.common.util.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import reivosar.common.util.collection.CollectionUtil;
import reivosar.common.util.promise.Promise;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
            Thread.sleep(7000);
            assertTrue(CollectionUtil.isEqualCollection(eventResults,
                    Arrays.asList("TestEventHandler")));
        }
        
        record SimpleTestEvent(LocalDateTime occurredOn) implements Event {
        }
        
        static class TestEventHandler {
            void handle(final SimpleTestEvent event) {
                System.out.println("TestEventHandler");
                eventResults.add("TestEventHandler");
            }
        }
    }
}