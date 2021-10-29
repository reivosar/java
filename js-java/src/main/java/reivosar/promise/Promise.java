package reivosar.promise;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import reivosar.common.util.model.Result;

public interface Promise<T> extends Result<T>
{
	public static <T>Promise<T> resolve(Supplier<T> supplier) {
		return new PromiseHandler<T>().resolve(supplier).await();
	}
	
	<R> Promise<R> then(Function<? super T, ? super R> mapper);

	Promise<T> onSuccess(final Consumer<T> consumer);

	Promise<T> onFailure(final Consumer<Throwable> consumer);
	
	Promise<T> ifErrorPresentThrow() throws PromiseException;
}
