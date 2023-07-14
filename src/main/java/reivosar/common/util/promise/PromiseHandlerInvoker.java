package reivosar.common.util.promise;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

abstract class PromiseHandlerInvoker<T> {
    
    private final ExecutorServiceProvider executorServiceProvider;
    private final PromiseTask<T> promiseTask;
    
    PromiseHandlerInvoker(final ExecutorServiceProvider executorServiceProvider,
                          final PromiseTask<T> promiseTask) {
        this.executorServiceProvider = executorServiceProvider;
        this.promiseTask = promiseTask;
    }
    
    Promise<T> invoke() {
        final CompletableFutures<T> futures = createCompletableFutures();
        watch(futures);
        return returnPromise(futures);
    }
    
    private Promise<T> returnPromise(CompletableFutures<T> futures) {
        if (this.executorServiceProvider.occurredTimeout()) {
            return new PromiseBuilder<T>().buildFailResultOtherPromise(new PromiseException("timeout occurred."));
        }
        return buildPromise(futures);
    }
    
    protected abstract Promise<T> buildPromise(final CompletableFutures<T> futures);
    
    private void watch(final CompletableFutures<T> futures) {
        final CompletableFuture<Void> all = futures.toAllOfFutures();
        this.executorServiceProvider.start();
        this.executorServiceProvider.awaitTermination();
        while (!all.isDone()) {
            if (this.executorServiceProvider.occurredTimeout()) {
                this.executorServiceProvider.stop();
            }
        }
    }
    
    private CompletableFuture<T> executeSupplyAsync(final Supplier<T> supplier) {
        final CompletableFuture<T> future = executorServiceProvider.executeSupplyAsync(supplier);
        future.whenComplete((result, error) -> {
            if (CompletableFutureResultWrapper.of(result, error).fail()) {
                this.executorServiceProvider.stop();
            }
        });
        return future;
    }
    
    private CompletableFutures<T> createCompletableFutures() {
        final CompletableFutures<T> result = new CompletableFutures<>();
        this.promiseTask.forEach(supplier -> result.add(executeSupplyAsync(supplier)));
        return result;
    }
    
}
