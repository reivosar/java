package reivosar.common.async.promise;

import reivosar.common.async.options.AsyncOptions;
import reivosar.common.async.options.MultiAsyncOptions;
import reivosar.common.async.options.SingleAsyncOptions;
import reivosar.common.data.model.Result;
import reivosar.common.lang.ObjectUtil;
import reivosar.common.lang.function.VoidConsumer;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * A Promise represents a value that may not yet be available but will be resolved in the future.
 * It is used for asynchronous operations and provides a way to handle the result or error of that operation
 * when it becomes available.
 *
 * <p>
 * Example usage:
 * <pre>
 *     Promise.resolve(() -> getAccessToken())
 *         .then(token -> processToken(token))
 *         .onSuccess(System.out::println)
 *         .onFailure(Throwable::printStackTrace);
 * </pre>
 *
 * @param <T> the type of the promised result
 */
public interface Promise<T> extends Result<T> {

    /**
     * Creates a resolved Promise with the result of the given supplier.
     *
     * @param supplier the supplier providing the result
     * @param <T>      the type of the promised result
     * @return a Promise that resolves with the supplier's result
     */
    static <T> Promise<T> resolve(Supplier<T> supplier) {
        return resolve(supplier, builder -> builder);
    }

    /**
     * Creates a resolved Promise with the result of the given supplier and a specified timeout.
     *
     * @param supplier the supplier providing the result
     * @param timeout  the maximum time in milliseconds to wait before considering the operation failed
     * @param <T>      the type of the promised result
     * @return a Promise that resolves with the supplier's result or fails if timeout is exceeded
     */
    static <T> Promise<T> resolve(Supplier<T> supplier, Long timeout) {
        ObjectUtil.requireNonNull("supplier", supplier);
        ObjectUtil.requireNonNull("timeout", timeout);
        return resolve(supplier, builder -> builder.timeout(timeout));
    }

    /**
     * Creates a resolved Promise using a supplier and custom Promise options.
     *
     * @param supplier     the supplier providing the result
     * @param configurator a function to configure the Promise options
     * @param <T>          the type of the promised result
     * @return a Promise that resolves with the supplier's result
     */
    static <T> Promise<T> resolve(Supplier<T> supplier,
                                  Function<SingleAsyncOptions.Builder, SingleAsyncOptions.Builder> configurator) {
        ObjectUtil.requireNonNull("supplier", supplier);
        ObjectUtil.requireNonNull("configurator", configurator);
        return resolve(supplier, AsyncOptions.builder().single(configurator).build());
    }

    /**
     * Creates a resolved Promise with specific Promise options.
     *
     * @param supplier the supplier providing the result
     * @param options  the Promise options to be used
     * @param <T>      the type of the promised result
     * @return a Promise that resolves with the supplier's result
     */
    static <T> Promise<T> resolve(Supplier<T> supplier, AsyncOptions options) {
        ObjectUtil.requireNonNull("supplier", supplier);
        ObjectUtil.requireNonNull("options", options);
        final PromiseHandler<T> promiseHandler = PromiseHandlerFactory.createPromiseHandler(options);
        return Objects.requireNonNull(promiseHandler).withSupplier(supplier).handle();
    }

    /**
     * Returns a Promise that resolves when all the provided {@link VoidConsumer}s have been executed.
     *
     * @param voidConsumers the collection of {@link VoidConsumer}s to execute
     * @return a Promise that resolves when all the provided {@link VoidConsumer}s have been executed
     */
    static Promise<Void> all(final Collection<VoidConsumer> voidConsumers) {
        return all(voidConsumers, builder -> builder);
    }

    /**
     * Returns a Promise that resolves when all the provided {@link VoidConsumer}s have been executed
     * with custom Promise options.
     *
     * @param voidConsumers the collection of {@link VoidConsumer}s to execute
     * @param configurator  a function to configure the Promise options
     * @return a Promise that resolves when all the provided {@link VoidConsumer}s have been executed
     */
    static Promise<Void> all(Collection<VoidConsumer> voidConsumers,
                             Function<MultiAsyncOptions.Builder, MultiAsyncOptions.Builder> configurator) {
        ObjectUtil.requireNonNull("voidConsumers", voidConsumers);
        ObjectUtil.requireNonNull("configurator", configurator);
        return all(voidConsumers, AsyncOptions.builder().multi(configurator).build());
    }

    /**
     * Executes all given {@link VoidConsumer}s asynchronously and resolves when all have completed.
     * <p>
     * This method processes a collection of {@link VoidConsumer}s concurrently using the provided
     * {@link AsyncOptions}. It returns a {@link Promise<Void>} that resolves once all consumers
     * have finished execution.
     * <p>
     * If any consumer throws an exception, the behavior depends on the specified {@link AsyncOptions}:
     * <ul>
     *     <li>If retries are enabled, failed consumers may be re-executed.</li>
     *     <li>If a timeout is set, execution may be terminated after the specified duration.</li>
     *     <li>If error handling is configured, the returned Promise may either resolve successfully
     *         or propagate the failure.</li>
     * </ul>
     *
     * @param voidConsumers the collection of {@link VoidConsumer}s to execute
     * @param options       the {@link AsyncOptions} defining execution behavior such as timeout and retries
     * @return a {@link Promise} that resolves when all consumers have completed execution
     */
    static Promise<Void> all(final Collection<VoidConsumer> voidConsumers, AsyncOptions options) {
        final PromiseHandler<Void> promiseHandler = PromiseHandlerFactory.createPromiseHandler(options);
        return promiseHandler.withVoidConsumers(voidConsumers).handle();
    }

    /**
     * Chains another asynchronous operation to be executed after this Promise is resolved.
     *
     * @param function the function to apply to the resolved value
     * @param <R>      the type of the resulting Promise
     * @return a new Promise representing the chained operation
     */
    default <R> Promise<R> then(Function<? super T, ? super R> function) {
        return then(function, builder -> builder);
    }

    /**
     * Chains another asynchronous operation with a timeout.
     *
     * @param function the function to apply to the resolved value
     * @param timeout  the maximum time in milliseconds to wait before considering the operation failed
     * @param <R>      the type of the resulting Promise
     * @return a new Promise representing the chained operation
     */
    default <R> Promise<R> then(Function<? super T, ? super R> function, Long timeout) {
        ObjectUtil.requireNonNull("function", function);
        ObjectUtil.requireNonNull("timeout", timeout);
        return then(function, builder -> builder.timeout(timeout));
    }

    /**
     * Chains another asynchronous operation with custom Promise options.
     *
     * @param function     the function to apply to the resolved value
     * @param configurator a function to configure the Promise options
     * @param <R>          the type of the resulting Promise
     * @return a new Promise representing the chained operation
     */
    default <R> Promise<R> then(Function<? super T, ? super R> function,
                                Function<SingleAsyncOptions.Builder, SingleAsyncOptions.Builder> configurator) {
        ObjectUtil.requireNonNull("function", function);
        ObjectUtil.requireNonNull("configurator", configurator);
        return then(function, AsyncOptions.builder().single(configurator).build());
    }

    /**
     * Chains another asynchronous operation with specific Promise options.
     *
     * @param function the function to apply to the resolved value
     * @param options  the Promise options to be used
     * @param <R>      the type of the resulting Promise
     * @return a new Promise representing the chained operation
     */
    <R> Promise<R> then(Function<? super T, ? super R> function, AsyncOptions options);

    /**
     * Attaches a callback to handle a successful resolution of the Promise.
     *
     * @param consumer the consumer that will be applied to the resolved value
     * @return the Promise with the callback attached
     */
    Promise<T> onSuccess(Consumer<T> consumer);

    /**
     * Attaches a callback to handle failure cases.
     *
     * @param consumer the consumer that will be applied to the exception
     * @return the Promise with the callback attached
     */
    Promise<T> onFailure(Consumer<Throwable> consumer);

    /**
     * Throws a {@link PromiseException} if the Promise has failed.
     *
     * @return the Promise instance
     * @throws PromiseException if the Promise has failed
     */
    Promise<T> throwIfError() throws PromiseException;
}
