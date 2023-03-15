package reivosar.common.util.promise;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PromiseTest {

    @Nested
    class TestForErrorCases {

        @Test
        void shouldNotBeCalledNextThenMethod_when_previousProcessHappensError() {
            final Promise<Integer> promise = Promise.resolve(() -> 1)
                    .then(i -> errorOccurred())//@ATTN
                    .then(i -> i + 2);

            errorAssertion(promise, "java.lang.IllegalStateException: error");
        }

        private Integer errorOccurred() {
            throw new IllegalStateException("error");
        }

        @Test
        void shouldNotBeCalledNextThenMethod_when_previousProcessReturnValueIsNull() {
            final Promise<Integer> promise = Promise.resolve(() -> 1)
                    .then(i -> (Integer) null)//@ATTN
                    .then(i -> i + 2);

            errorAssertion(promise, "result is null.");
        }

        @Test
        void shouldNotBeCalledNextThenMethod_when_timeoutOccurred() {
            final Promise<Integer> promise = Promise.resolve(() -> 1, 1)
                    .then(i -> 1 + timeoutOccurred(), 1)//@ATTN
                    .then(i -> i + 2);

            errorAssertion(promise, "timeout occurred.");
        }

        private Integer timeoutOccurred() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 1;
        }

        private <T> void errorAssertion(Promise<T> promise, String errorMessage) {
            assertEquals(Optional.empty(), promise.result());
            assertFalse(promise.success());
            assertTrue(promise.fail());
            assertFalse(promise.result().isPresent());
            assertTrue(promise.error().isPresent());
            promise.onSuccess(result -> fail());
            promise.onFailure(error -> assertEquals(errorMessage, error.getMessage()));
            assertThrows(PromiseException.class, promise::throwIfError);
        }
    }

    @Nested
    class TestForTypeConversionAndHandoverOfResultInTheThenMethod {

        @Test
        void int_to_int_is_int() {
            final Promise<Integer> promise = Promise.resolve(() -> 1)
                    .then(i -> i + 1);
            assertion(promise, 2);
        }

        @Test
        void int_to_string_is_string() {
            final Promise<String> promise = Promise.resolve(() -> 2)
                    .then(i -> "test" + i.toString());
            assertion(promise, "test2");
        }

        @Test
        void int_to_string_to_int_is_int() {
            final Promise<Integer> promise = Promise.resolve(() -> 1)
                    .then(i -> i.toString() + "2")
                    .then(str -> Integer.parseInt(str) * 3);
            assertion(promise, 36);
        }

        private <T> void assertion(final Promise<T> promise, final T value) {
            assertEquals(Optional.of(value), promise.result());
            assertTrue(promise.success());
            assertFalse(promise.fail());
            assertTrue(promise.result().isPresent());
            assertFalse(promise.error().isPresent());
            promise.onSuccess(result -> assertEquals(value, result));
            promise.onFailure(error -> fail());
            assertDoesNotThrow(promise::throwIfError);
        }
    }
}
