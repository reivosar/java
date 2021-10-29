package reivosar.promise;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class ExecutorServiceProvider
{
	private final ExecutorService executorService;
	private final long timeout;

	private boolean occurredTimeout;

	public ExecutorServiceProvider(ExecutorService executorService, long timeout) {
		this.executorService = executorService;
		this.timeout         = timeout;
	}

	public void start() {
		this.executorService.shutdown();
	}

	public void awaitTermination() {
		try {
			if (!this.executorService.awaitTermination(timeout, TimeUnit.SECONDS))
				this.occurredTimeout = true;
		} catch (InterruptedException e) {
			this.occurredTimeout = true;
		}
	}

	public void stop() {
		this.executorService.shutdownNow();
	}

	public <T>CompletableFuture<T> executeSupplyAsynch(final Supplier<T> supplier) {
		return CompletableFuture.supplyAsync(supplier, executorService);
	}

	public boolean occurredTimeout() {
		return this.occurredTimeout;
	}
}
