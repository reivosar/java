package reivosar.common.util.promise;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

class CompletableFutures<T> {
    
    private final Collection<CompletableFuture<T>> futures;
    
    CompletableFutures() {
        this.futures = new LinkedHashSet<>();
    }
    
    CompletableFutures<T> add(final CompletableFuture<T> completableFutures) {
        this.futures.addAll(List.of(completableFutures));
        return this;
    }
    
    CompletableFuture<Void> toAllOfFutures() {
        return CompletableFuture.allOf(
                this.futures.toArray(
                        new CompletableFuture[futures.size()])
        );
    }
    
    Collection<CompletableFuture<T>> all() {
        return Collections.unmodifiableCollection(this.futures);
    }
    
    boolean success() {
        return !fail();
    }
    
    boolean fail() {
        return hasErrors();
    }
    
    Collection<Optional<T>> results() {
        return this.all().stream()
                .map(CompletableFutureResultWrapper::of)
                .filter(CompletableFutureResultWrapper::success)
                .map(CompletableFutureResultWrapper::result)
                .collect(Collectors.toUnmodifiableList());
    }
    
    Collection<Throwable> errors() {
        return this.all().stream()
                .map(CompletableFutureResultWrapper::of)
                .filter(CompletableFutureResultWrapper::fail)
                .map(CompletableFutureResultWrapper::error)
                .collect(Collectors.toUnmodifiableList());
    }
    
    private boolean hasErrors() {
        return !errors().isEmpty();
    }
}
