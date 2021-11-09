package reivosar.common.util.promise;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PromiseTest {
	
	@Nested
	class TestForThenMethod 
	{
		@Nested
		class NotCalled {
			@Test
			void If_the_previous_process_happens_error() {
				Promise<Integer> promise = Promise.resolve(() -> 
							Optional.ofNullable((Integer)null)
								.orElseThrow(() -> {
									throw new IllegalStateException("error");
								}))
						.then(i -> i + 1);
				
				assertThat(promise.success(),            is(false));
				assertThat(promise.fail(),               is(true));
				assertThat(promise.result().isPresent(), is(false));
				assertThat(promise.error().isPresent(),  is(true));
				promise.onSuccess(result -> fail());
				promise.onFailure(error  -> 
					assertThat(error.getCause().getMessage(), is("error")));
			}
			@Test
			void If_the_result_of_the_previous_process_is_empty() 
			{
				Promise<Integer> promise = Promise.resolve(() -> (Integer)null)
						.then(i -> i + 1);
				
				assertThat(promise.success(),            is(false));
				assertThat(promise.fail(),               is(true));
				assertThat(promise.result().isPresent(), is(false));
				assertThat(promise.error().isPresent(),  is(true));
				promise.onSuccess(result -> fail());
				promise.onFailure(error  -> 
					assertThat(error.getMessage(), is("result is null.")));
			}
		}
		
		@Nested
		class TypeConversion {
			private <T>void assertion(Promise<T> promise, T value) {
				assertThat(promise.success(),            is(true));
				assertThat(promise.fail(),               is(false));
				assertThat(promise.result().isPresent(), is(true));
				assertThat(promise.result().get(),       is(value));
				assertThat(promise.error().isPresent(),  is(false));
				promise.onSuccess(result -> result.equals(value));
				promise.onFailure(error  -> fail());
			}
			@Test
			void int_to_int() {
				Promise<Integer> promise = Promise.resolve(() -> 1)
						.then(i -> i + 1);
				assertion(promise, 2);
			}
			@Test
			void int_to_string() {
				Promise<String> promise = Promise.resolve(() -> 2)
						.then(i -> "test" + i.toString());
				assertion(promise, "test2");
			}
			@Test
			void int_to_string_to_int() {
				Promise<Integer> promise = Promise.resolve(() -> 1)
						.then(i -> i.toString() + "2")
						.then(str -> Integer.parseInt(str) * 3);
				assertion(promise, 36);
			}	
		}
	}
}
