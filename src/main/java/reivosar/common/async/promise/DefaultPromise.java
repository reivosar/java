package reivosar.common.async.promise;

import java.util.function.Consumer;
import java.util.function.Function;

abstract class DefaultPromise<T> implements Promise<T> {
    
    @Override
    public <R> Promise<R> then(final Function<? super T, ? super R> function) {
        return then(function, PromiseConfig.DEFAULT_CONFIG.timeout);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <R> Promise<R> then(final Function<? super T, ? super R> function, long timeout) {
        if (fail()) {
            return buildFailResultOtherPromise(this);
        }
        if (result().isEmpty()) {
            // When this method is called, the previous Promise result must not be null.
            return buildFailResultOtherPromise(new PromiseException("result is null."));
        }
        final PromiseHandler<R> promiseHandler = PromiseHandlerFactory.createWithTimeout(timeout);
        final T result = result().get();
        return promiseHandler.withSupplier(() -> (R) function.apply(result)).handle();
    }
    
    private <R> Promise<R> buildFailResultOtherPromise(final Throwable error) {
        return new PromiseBuilder<T>().buildFailResultOtherPromise(error);
    }
    
    private <R> Promise<R> buildFailResultOtherPromise(final Promise<T> originalPromise) {
        return new PromiseBuilder<T>().buildFailResultOtherPromise(originalPromise);
    }
    
    @Override
    public Promise<T> throwIfError() throws PromiseException {
        error().ifPresent(error -> {
            throw new PromiseException(error);
        });
        return this;
    }
    
    @Override
    public Promise<T> onSuccess(final Consumer<T> consumer) {
        if (success())
            result().ifPresent(consumer);
        return this;
    }
    
    @Override
    public Promise<T> onFailure(final Consumer<Throwable> consumer) {
        if (fail())
            error().ifPresent(consumer);
        return this;
    }
    
}
