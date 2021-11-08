package reivosar.common.util.promise;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import reivosar.common.util.log.Loggers;

class PromiseHandlerInvoker<T>
{
	private final Loggers loggers = Loggers.getLoggers(PromiseHandlerInvoker.class);

	private final ExecutorServiceProvider executorServiceProvider;
	private final PromiseTask<T> promiseTask;

	PromiseHandlerInvoker(ExecutorServiceProvider executorServiceProvider, PromiseTask<T> promiseTask) {
		this.executorServiceProvider = executorServiceProvider;
		this.promiseTask = promiseTask;
	}

	void async() {
		try {
			this.promiseTask.forEach(executorServiceProvider::executeSupplyAsynch);
			this.executorServiceProvider.start();
		} catch (Exception e) {
			loggers.error("Promise Task Error.", e);
		}
	}

	Promise<T> await() {
		final CompletableFutures<T> futures = createCompletableFutures();
		watch (futures);
		return new PromiseBuilder<T>().buildFromCompletableFutures(futures);
	}

	private void watch(CompletableFutures<T> futures) {
		final CompletableFuture<Void> all = futures.toAllOfFutures();
		this.executorServiceProvider.start();
		this.executorServiceProvider.awaitTermination();
		while(!all.isDone()) {
			if (this.executorServiceProvider.occurredTimeout())
				this.executorServiceProvider.stop();
		}
	}

	private CompletableFuture<T> executeSupplyAsynch(final Supplier<T> supplier) {
		final CompletableFuture<T> future = executorServiceProvider.executeSupplyAsynch(supplier);
		future.whenComplete((result, error) -> {
			if (CompletableFutureResultWrapper.of(result, error).fail()) {
				this.executorServiceProvider.stop();
			}
		});
		return future;
	}

	private CompletableFutures<T> createCompletableFutures() {
		final CompletableFutures<T> result = new CompletableFutures<>();
		this.promiseTask.forEach(supplier -> result.add(executeSupplyAsynch(supplier)));
		return result;
	}
}

