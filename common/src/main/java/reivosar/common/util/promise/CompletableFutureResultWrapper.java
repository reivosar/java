package reivosar.common.util.promise;

import java.util.Optional;
import java.util.concurrent.Future;

class CompletableFutureResultWrapper<T> {

	private final T result;
	private final Throwable error;

	CompletableFutureResultWrapper(final T result, final Throwable error) {
		this.result = result;
		this.error  = error;
	}

	static <T> CompletableFutureResultWrapper<T> of(final Future<T> future) {
		try {
			return new CompletableFutureResultWrapper<>(future.get(), null);
		} catch (Throwable error) {
			return new CompletableFutureResultWrapper<>(null, error);
		}
	}

	static <T> CompletableFutureResultWrapper<T> of(final T result, final Throwable error) {
		return new CompletableFutureResultWrapper<>(result, error);
	}

	boolean success() {
		return !fail();
	}

	boolean fail() {
		return error() != null;
	}

	Optional<T> result() {
		return success() ? Optional.ofNullable(this.result): Optional.empty();
	}

	Throwable error() {
		return this.error;
	}
}
