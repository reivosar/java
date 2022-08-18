package reivosar.common.util.promise;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PromiseTest {

    @Nested
    class TestForErrorCases {

        @Test
        void shouldNotBeCalled_when_PreviousProcessHappensError() {
            Promise<Integer> promise = Promise.resolve(this::errorOccurred) //@ATTN
                    .then(i -> i + 1)
                    .then(i -> i + 2);
            assertThat(promise.success(),            is(false));
            assertThat(promise.fail(),               is(true));
            assertThat(promise.result().isPresent(), is(false));
            assertThat(promise.error().isPresent(),  is(true));
            promise.onSuccess(result -> fail());
            promise.onFailure(error  -> assertThat(error.getCause().getMessage(), is("error")));
            assertThrows(PromiseException.class, promise::ifErrorPresentThrow);
        }

        @Test
        void shouldNotBeCalled_when_PreviousProcessReturnValueIsNull() {
            Promise<Integer> promise = Promise.resolve(() -> 1)
                    .then(i -> (Integer) null)//@ATTN
                    .then(i -> i + 2);
            assertThat(promise.success(),            is(false));
            assertThat(promise.fail(),               is(true));
            assertThat(promise.result().isPresent(), is(false));
            assertThat(promise.error().isPresent(),  is(true));
            promise.onSuccess(result -> fail());
            promise.onFailure(error  -> assertThat(error.getMessage(), is("result is null.")));
            assertThrows(PromiseException.class, promise::ifErrorPresentThrow);
        }

        private Integer errorOccurred() {
            throw new IllegalStateException("error");
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
                    .then(i   -> i.toString() + "2")
                    .then(str -> Integer.parseInt(str) * 3);
            assertion(promise, 36);
        }

        private <T>void assertion(final Promise<T> promise, final T value) {
            assertThat(promise.success(),            is(true));
            assertThat(promise.fail(),               is(false));
            assertThat(promise.result().isPresent(), is(true));
            assertThat(promise.result().get(),       is(value));
            assertThat(promise.error().isPresent(),  is(false));
            promise.onSuccess(result -> assertThat(result, is(value)));
            promise.onFailure(error  -> fail());
            assertDoesNotThrow(promise::ifErrorPresentThrow);
        }
    }
}
