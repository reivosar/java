package reivosar.common.async.promise;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

class CompletableFutures<T> {
    
    private final Collection<CompletableFuture<T>> futures;
    
    CompletableFutures() {
        this.futures = new LinkedHashSet<>();
    }
    
    void add(final CompletableFuture<T> completableFutures) {
        this.futures.add(completableFutures);
    }
    
    CompletableFuture<Void> toAllOfFutures() {
        return CompletableFuture.allOf(this.futures.toArray(new CompletableFuture[futures.size()]));
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
                .map(CompletableFutureResultWrapper::result).toList();
    }
    
    Collection<Throwable> errors() {
        return this.all().stream()
                .map(CompletableFutureResultWrapper::of)
                .filter(CompletableFutureResultWrapper::fail)
                .map(CompletableFutureResultWrapper::error).toList();
    }
    
    private boolean hasErrors() {
        return !errors().isEmpty();
    }
}
