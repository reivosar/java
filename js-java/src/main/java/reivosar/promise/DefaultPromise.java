package reivosar.promise;

import java.util.function.Consumer;
import java.util.function.Function;

abstract class DefaultPromise<T> implements Promise<T> {

	@SuppressWarnings("unchecked")
	@Override
	public <R> Promise<R> then(Function<? super T, ? super R> mapper) {
		if (fail())
			return buildFailResultOtherPromise(this);
		if (result().isEmpty())
			return buildFailResultOtherPromise(new PromiseException("result is null."));
		return new PromiseHandler<R>().resolve(
				() -> (R) mapper.apply(result().get())
			).await();
	}
	
	private <R>Promise<R> buildFailResultOtherPromise(Throwable error) {
		return new PromiseBuilder<T>().buildFailResultOtherPromise(error);
	}
	
	private <R>Promise<R> buildFailResultOtherPromise(Promise<T> originalPromise) {
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
	public Promise<T> onSuccess(Consumer<T> consumer) {
		if (success())
			result().ifPresent(consumer);
		return this;
	}
	
	@Override
	public Promise<T> onFailure(Consumer<Throwable> consumer) {
		if (fail())
			error().ifPresent(consumer);
		return this;
	}

}
