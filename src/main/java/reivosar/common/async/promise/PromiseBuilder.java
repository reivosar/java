package reivosar.common.async.promise;

import java.util.Optional;

class PromiseBuilder<T> {
    
    Promise<T> buildFromSinglePromise(final CompletableFutures<T> futures) {
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
    
    Promise<T> buildFromMultiPromises(final CompletableFutures<T> futures) {
        return new DefaultPromise<>() {
            public boolean success() {
                return futures.success();
            }
            
            public boolean fail() {
                return futures.fail();
            }
            
            public Optional<T> result() {
                return Optional.empty();
            }
            
            public Optional<Throwable> error() {
                if (!fail()) return Optional.empty();
                return futures.errors().stream().findFirst();
            }
        };
    }
    
    <R> Promise<R> buildFailResultOtherPromise(final Throwable error) {
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
    
    <R> Promise<R> buildFailResultOtherPromise(final Promise<T> promise) {
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
                return convertToPromiseException();
            }
            
            private Optional<Throwable> convertToPromiseException() {
                if (promise.error().isEmpty()) {
                    return Optional.empty();
                }
                final Throwable throwable = promise.error().get();
                if (throwable instanceof PromiseException) {
                    return promise.error();
                }
                return Optional.of(new PromiseException(throwable.getMessage(), throwable));
            }
        };
    }
}
