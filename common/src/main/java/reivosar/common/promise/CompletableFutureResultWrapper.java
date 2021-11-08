package reivosar.common.promise;

import java.util.Optional;
import java.util.concurrent.Future;

class CompletableFutureResultWrapper<T> {

	private final Optional<T> result;
	private final Throwable error;

	CompletableFutureResultWrapper(T result, Throwable error) {
		this.result = Optional.ofNullable(result);
		this.error  = error;
	}

	static <T> CompletableFutureResultWrapper<T> of(final Future<T> future) {
		try {
			return new CompletableFutureResultWrapper<>(future.get(), null);
		} catch (Throwable error) {
			return new CompletableFutureResultWrapper<>(null, error);
		}
	}

	static <T> CompletableFutureResultWrapper<T> of(T result, Throwable error) {
		return new CompletableFutureResultWrapper<>(result, error);
	}

	boolean success() {
		return !fail();
	}

	boolean fail() {
		return error() != null;
	}

	Optional<T> result() {
		return success() ? this.result : Optional.empty();
	}

	Throwable error() {
		if (this.error == null)
			return null;
		return this.error;
	}
}
