package reivosar.common.util.promise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

class PromiseHandler<T>
{
	private final ExecutorServiceProvider executorServiceProvider;
	private final PromiseTask<T> promiseTask;

	private static final long DEFAULT_TIMEOUT = 30;

	PromiseHandler() {
		this(1);
	}

	PromiseHandler(int multiple) {
		this(Executors.newFixedThreadPool(multiple));
	}

	PromiseHandler(final ExecutorService executorService) {
		this(executorService, DEFAULT_TIMEOUT);
	}

	PromiseHandler(final ExecutorService executorService, final long timeout) {
		this.executorServiceProvider = new ExecutorServiceProvider(executorService, timeout);
		this.promiseTask = new PromiseTask<>();
	}

	PromiseHandler<T> resolve(final Supplier<T> supplier) {
		this.promiseTask.addTask(supplier);
		return this;
	}

	void async() {
		new PromiseHandlerInvoker<T>(executorServiceProvider, promiseTask).async();
	}

	Promise<T> await() {
		return new PromiseHandlerInvoker<T>(executorServiceProvider, promiseTask).await();
	}
}
