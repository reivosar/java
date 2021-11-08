package reivosar.common.util.promise;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

class ExecutorServiceProvider
{
	private final ExecutorService executorService;
	private final long timeout;

	private boolean occurredTimeout;

	ExecutorServiceProvider(ExecutorService executorService, long timeout) {
		this.executorService = executorService;
		this.timeout         = timeout;
	}

	void start() {
		this.executorService.shutdown();
	}

	void awaitTermination() {
		try {
			if (!this.executorService.awaitTermination(timeout, TimeUnit.SECONDS))
				this.occurredTimeout = true;
		} catch (InterruptedException e) {
			this.occurredTimeout = true;
		}
	}

	void stop() {
		this.executorService.shutdownNow();
	}

	<T>CompletableFuture<T> executeSupplyAsynch(final Supplier<T> supplier) {
		return CompletableFuture.supplyAsync(supplier, executorService);
	}

	boolean occurredTimeout() {
		return this.occurredTimeout;
	}
}
