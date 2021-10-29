package reivosar.promise;

import java.util.Optional;

class PromiseBuilder<T> {
	
	<R>Promise<R> buildEmptyResultOtherPromise(final Promise<T> promise) {
		return new DefaultPromise<R>() {
			public boolean success() {
				return promise.success();
			}
			public boolean fail() {
				return promise.fail();
			}
			public Optional<R> result() {
				return Optional.empty();
			}
			public Optional<Throwable> error() {
				return promise.error();
			}
		};
	}

	Promise<T> buildFromCompletableFutures(final CompletableFutures<T> futures) {
		return new DefaultPromise<T>() {
			public boolean success() {
				return futures.success();
			}
			public boolean fail() {
				return futures.fail();
			}
			public Optional<T> result() {
				if (!success()) return Optional.empty();
				return futures.results().stream().findFirst().orElseGet(Optional::empty);
			}
			public Optional<Throwable> error() {
				if (!fail()) return Optional.empty();
				return futures.errors().stream().findFirst();
			}
		};
	}
	
	<R>Promise<R> buildFailResultOtherPromise(Throwable error) {
		return new DefaultPromise<R>() {
			@Override
			public boolean success() {
				return false;
			}

			@Override
			public boolean fail() {
				return true;
			}

			@Override
			public Optional<R> result() {
				return Optional.empty();
			}

			@Override
			public Optional<Throwable> error() {
				return Optional.of(error);
			}
		};	
	}
	
	<R>Promise<R> buildFailResultOtherPromise(Promise<T> promise) {
		return new DefaultPromise<R>() {
			@Override
			public boolean success() {
				return false;
			}

			@Override
			public boolean fail() {
				return true;
			}

			@Override
			public Optional<R> result() {
				return Optional.empty();
			}

			@Override
			public Optional<Throwable> error() {
				return promise.error();
			}
		};	
	}
}
