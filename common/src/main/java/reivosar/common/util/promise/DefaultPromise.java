package reivosar.common.util.promise;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

abstract class DefaultPromise<T> implements Promise<T> {
    
    @SuppressWarnings("unchecked")
    @Override
    public <R> Promise<R> then(final Function<? super T, ? super R> function) {
        if (fail())
            return buildFailResultOtherPromise(this);
        if (result().isEmpty())
            return buildFailResultOtherPromise(new PromiseException("result is null."));
        return new PromiseHandler<R>().resolve(
                () -> (R) function.apply(result().get())
        ).await();
    }
    
    @Override
    public Promise<T> then(final Supplier<T> supplier) {
        if (fail())
            return this;
        return new PromiseHandler<T>().resolve(supplier).await();
    }
    
    private <R> Promise<R> buildFailResultOtherPromise(final Throwable error) {
        return new PromiseBuilder<T>().buildFailResultOtherPromise(error);
    }
    
    private <R> Promise<R> buildFailResultOtherPromise(final Promise<T> originalPromise) {
        return new PromiseBuilder<T>().buildFailResultOtherPromise(originalPromise);
    }
    
    @Override
    public Promise<T> ifErrorPresentThrow() throws PromiseException {
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