package reivosar.common.async.promise;

import reivosar.common.lang.function.VoidConsumer;
import reivosar.common.lang.ObjectUtil;
import reivosar.common.data.model.Result;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A Promise represents a value that may not yet be available but will be resolved in the future.
 * It is used for asynchronous operations and provides a way to handle the result or error of that operation
 * when it becomes available.
 *
 * <p>
 * Instructions are below.
 * <pre>
 *     Promise.resolve(() -> getAccessToken())
 *         .then (token -> ...)
 *         .onSuccess (System.out::println);
 * </pre>
 *
 * @param <T> the type of the promised result
 */
public interface Promise<T> extends Result<T> {
    
    /**
     * Creates and returns a new Promise that will be resolved with the result of the specified supplier.
     *
     * @param supplier the supplier that will provide the result for the Promise
     * @param <T>      the type of the result provided by the supplier
     * @return a new Promise that will be resolved with the result of the specified supplier
     * @throws NullPointerException if the specified supplier is null
     */
    static <T> Promise<T> resolve(Supplier<T> supplier) {
        final PromiseHandler<T> promiseHandler = PromiseHandlerFactory.createWithDefaultSettings();
        return promiseHandler.withSupplier(supplier).handle();
    }
    
    /**
     * Creates and returns a new Promise that will be resolved with the result of the specified supplier, or will fail
     * with a TimeoutException if the specified timeout is exceeded before the supplier returns.
     *
     * @param supplier the supplier that will provide the result for the Promise
     * @param timeout  the maximum amount of time to wait for the supplier to return
     * @param <T>      the type of the result provided by the supplier
     * @return a new Promise that will be resolved with the result of the specified supplier, or will fail with a
     * TimeoutException if the specified timeout is exceeded before the supplier returns.
     * @throws NullPointerException if the specified supplier is null
     */
    static <T> Promise<T> resolve(Supplier<T> supplier, long timeout) {
        ObjectUtil.requireNonNull("supplier", supplier);
        final PromiseHandler<T> promiseHandler = PromiseHandlerFactory.createWithTimeout(timeout);
        return promiseHandler.withSupplier(supplier).handle();
    }
    
    /**
     * Attaches a callback to the Promise that will be executed when the Promise is resolved successfully with a value.
     *
     * @param function the function that will be applied to the Promise's value
     * @param <R>      the type of the result of the function
     * @return a new Promise that will be resolved with the result of the function
     */
    <R> Promise<R> then(Function<? super T, ? super R> function);
    
    /**
     * Attaches a callback to the Promise that will be executed when the Promise is resolved successfully with a value, or
     * will fail with a TimeoutException if the specified timeout is exceeded before the Promise is resolved.
     *
     * @param function the function that will be applied to the Promise's value
     * @param timeout  the maximum amount of time to wait for the Promise to be resolved
     * @param <R>      the type of the result of the function
     * @return a new Promise that will be resolved with the result of the function, or will fail with a TimeoutException
     * if the specified timeout is exceeded before the Promise is resolved.
     */
    <R> Promise<R> then(Function<? super T, ? super R> function, long timeout);
    
    /**
     * Attaches a callback to the Promise that will be executed when the Promise is resolved successfully with a value.
     *
     * @param consumer the consumer that will be applied to the Promise's value
     * @return the Promise with the callback attached
     */
    Promise<T> onSuccess(Consumer<T> consumer);
    
    /**
     * Attaches a callback to the Promise that will be executed when the Promise fails with an exception.
     *
     * @param consumer the consumer that will be applied to the Promise's exception
     * @return the Promise with the callback attached
     */
    Promise<T> onFailure(Consumer<Throwable> consumer);
    
    /**
     * Throws a PromiseException if the Promise has failed.
     *
     * @return the Promise instance
     * @throws PromiseException if the Promise has failed
     */
    Promise<T> throwIfError() throws PromiseException;
    
    /**
     * Returns a {@link Promise} that resolves when all the provided {@link VoidConsumer}s have been executed.
     *
     * @param voidConsumers the collection of {@link VoidConsumer}s to execute.
     * @return a {@link Promise} that resolves when all the provided {@link VoidConsumer}s have been executed.
     */
    static Promise<Void> all(final Collection<VoidConsumer> voidConsumers) {
        final PromiseHandler<Void> promiseHandler = PromiseHandlerFactory.createMultiplePromiseHandler();
        return promiseHandler.withVoidConsumers(voidConsumers).handle();
    }
}
