package reivosar.promise;

import java.util.Optional;
import java.util.concurrent.Future;

public class CompletableFutureResultWrapper<T> {

	private final Optional<T> result;
	private final Throwable error;

	public CompletableFutureResultWrapper(T result, Throwable error) {
		this.result = Optional.ofNullable(result);
		this.error  = error;
	}

	public static <T> CompletableFutureResultWrapper<T> of(final Future<T> future) {
		try {
			return new CompletableFutureResultWrapper<>(future.get(), null);
		} catch (Throwable error) {
			return new CompletableFutureResultWrapper<>(null, error);
		}
	}

	public static <T> CompletableFutureResultWrapper<T> of(T result, Throwable error) {
		return new CompletableFutureResultWrapper<>(result, error);
	}

	public boolean success() {
		return !fail();
	}

	public boolean fail() {
		return error() != null;
	}

	public Optional<T> result() {
		return success() ? this.result : Optional.empty();
	}

	public Throwable error() {
		if (this.error == null)
			return null;
		return this.error;
	}
}
