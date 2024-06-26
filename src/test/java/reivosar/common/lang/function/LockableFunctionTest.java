package reivosar.common.lang.function;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

class LockableFunctionTest {
    
    @Nested
    class WithMethodTests {
        
        private LockableFunction lockableFunction;
        
        @BeforeEach
        void setUp() {
            lockableFunction = new LockableFunction();
        }
        
        @Test
        void shouldExecuteSupplierWithinLockedSectionAndReturnResult() {
            // given
            AtomicInteger counter = new AtomicInteger(0);
            Supplier<Integer> supplier = () -> {
                counter.incrementAndGet();
                return counter.get();
            };
            
            // when
            Integer result = lockableFunction.lockOn(supplier);
            
            // then
            assertEquals(1, result);
        }
        
        @Test
        void shouldExecuteVoidConsumerWithinLockedSection() {
            // given
            AtomicBoolean executed = new AtomicBoolean(false);
            VoidConsumer consumer = () -> executed.set(true);
            
            // when
            lockableFunction.lockOn(consumer);
            
            // then
            assertTrue(executed.get());
        }
        
        @Test
        void shouldHandleExceptionThrownBySupplier() {
            // given
            Supplier<Integer> supplier = () -> {
                throw new RuntimeException("test exception");
            };
            
            // when
            assertThrows(RuntimeException.class, () -> lockableFunction.lockOn(supplier));
        }
        
        @Test
        void shouldHandleExceptionThrownByConsumer() {
            // given
            VoidConsumer consumer = () -> {
                throw new RuntimeException("test exception");
            };
            
            // when
            assertThrows(RuntimeException.class, () -> lockableFunction.lockOn(consumer));
        }
        
        @Test
        void shouldNotBlockOtherThreads() throws InterruptedException {
            // given
            AtomicInteger counter = new AtomicInteger(0);
            Runnable runnable = () -> lockableFunction.lockOn(counter::incrementAndGet);
            
            // when
            Thread thread1 = new Thread(runnable);
            Thread thread2 = new Thread(runnable);
            thread1.start();
            thread2.start();
            thread1.join();
            thread2.join();
            
            // then
            assertEquals(2, counter.get());
        }
        
    }
}