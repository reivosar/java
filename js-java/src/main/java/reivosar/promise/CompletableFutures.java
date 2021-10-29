package reivosar.promise;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class CompletableFutures<T> {

	private final Collection<CompletableFuture<T>> futures;

	public CompletableFutures() {
		this.futures = new LinkedHashSet<>();
	}

	public CompletableFutures<T> add(CompletableFuture<T> completableFutures) {
		this.futures.addAll(Arrays.asList(completableFutures));
		return this;
	}

	public CompletableFuture<Void> toAllOfFutures() {
		return CompletableFuture.allOf(
			this.futures.toArray(
				new CompletableFuture[futures.size()])
		);
	}

	public Collection<CompletableFuture<T>> all() {
		return Collections.unmodifiableCollection(this.futures);
	}

	public boolean success() {
		return !fail();
	}

	public boolean fail() {
		return hasErrors();
	}

	public Collection<Optional<T>> results() {
		return this.all().stream()
			.map     (CompletableFutureResultWrapper::of)
			.filter  (CompletableFutureResultWrapper::success)
			.map     (CompletableFutureResultWrapper::result)
			.collect (Collectors.toUnmodifiableList());
	}

	public Collection<Throwable> errors() {
		return this.all().stream()
			.map     (CompletableFutureResultWrapper::of)
			.filter  (CompletableFutureResultWrapper::fail)
			.map     (CompletableFutureResultWrapper::error)
			.collect (Collectors.toUnmodifiableList());
	}

	private boolean hasErrors() {
		return !errors().isEmpty();
	}
}
