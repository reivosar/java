package reivosar.common.util.promise;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
            assertThat(promise.success(), is(false));
            assertThat(promise.fail(), is(true));
            assertThat(promise.result().isPresent(), is(false));
            assertThat(promise.error().isPresent(), is(true));
            promise.onSuccess(result -> fail());
            promise.onFailure(error -> assertThat(error.getMessage(), is(errorMessage)));
            assertThrows(PromiseException.class, promise::ifErrorPresentThrow);
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
            assertThat(promise.success(), is(true));
            assertThat(promise.fail(), is(false));
            assertThat(promise.result().isPresent(), is(true));
            assertThat(promise.result().get(), is(value));
            assertThat(promise.error().isPresent(), is(false));
            promise.onSuccess(result -> assertThat(result, is(value)));
            promise.onFailure(error -> fail());
            assertDoesNotThrow(promise::ifErrorPresentThrow);
        }
    }
}
