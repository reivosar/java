package reivosar.common.util.promise;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import reivosar.common.util.model.Result;

/**
 * The Promise object represents the result of the completion (or failure)
 * of an asynchronous operation and the value of the result.
 * 
 * Instructions are below.
 * <pre>
 *     Promise.resolve(() -> getAccessToken())
 *         .then (token -> ...) 
 *         .onSuccess (System.out::println);
 * </pre> 
 *  
 * @param <T> The type of result that this Promise holds
 */
public interface Promise<T> extends Result<T>
{
	/**
	 * Asynchronously processes the behavior of the 
	 * Supplier passed as an argument. 
	 * 
	 * It waits for a certain period of time until 
	 * the processing is completed, and returns a 
	 * Promise object holding the result when it is completed.
	 * 
	 * @param <T> The type of result that this Promise holds
	 * @param supplier supplier to generate Promise
	 * @return {@link Promise}
	 */
	static <T>Promise<T> resolve(Supplier<T> supplier) {
        return new PromiseHandler<T>().resolve(supplier).await();
	}
	
	/**
	 * Generate a new Promise based on the results processed by 
	 * the previous Promise. If an exception has been thrown 
	 * before, this method will be skipped.
	 * 
	 * @param <R> The type of result that this Promise holds
	 * @param function function to receive and handle the result
	 * @return {@link Promise}
	 */
	<R> Promise<R> then(Function<? super T, ? super R> function);

    /**
     * Generate a new promise based on the suplier passed in the argument.
     * If an exception has been thrown before, this method will be skipped.
     *
     * @param supplier supplier to generate Promise
     * @return {@link Promise}
     */
    Promise<T> then(Supplier<T> supplier);

	/**
	 * If the series of processing is successful, the result of 
	 * the processing is handled with the behavior of the Consumer 
	 * object passed as an argument.
	 * 
	 * If the process fails, this method will not be called.
	 * 
	 * @param consumer consumer to receive and process the results of the previous promise
	 * @return {@link Promise}
	 */
	Promise<T> onSuccess(final Consumer<T> consumer);

	/**
	 * If an exception was occured in the previous process, 
	 * Exception object is handled with the behavior of the Consumer 
	 * object passed as an argument. 
	 * 
	 * If the processing succeeds, this method will not be called.
	 * 
	 * @param consumer consumer to receive and process the results of the previous promise
	 * @return {@link Promise}
	 */
	Promise<T> onFailure(final Consumer<Throwable> consumer);
	
	/**
	 * An exception will be thrown if the previous process 
	 * threw an exception.
	 * 
	 * @return {@link Promise}
	 * @throws PromiseException　This exception will be thrown if an error occurs during promise generation.
	 */
	Promise<T> ifErrorPresentThrow() throws PromiseException;
}
