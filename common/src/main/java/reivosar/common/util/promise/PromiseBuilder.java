package reivosar.common.util.promise;

import java.util.Optional;

class PromiseBuilder<T> {

    Promise<T> buildFromCompletableFutures(final CompletableFutures<T> futures) {
		return new DefaultPromise<>() {
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
	
	<R>Promise<R> buildFailResultOtherPromise(final Throwable error) {
		return new DefaultPromise<>() {
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
	
	<R>Promise<R> buildFailResultOtherPromise(final Promise<T> promise) {
		return new DefaultPromise<>() {
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
