package reivosar.common.util.promise;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

class PromiseHandler<T> {
    private final ExecutorServiceProvider executorServiceProvider;
    private final PromiseTask<T> promiseTask;
    
    private static final long DEFAULT_TIMEOUT = 30;
    
    PromiseHandler() {
        this(1);
    }
    
    PromiseHandler(final int multiple) {
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
        return resolve(List.of(supplier));
    }
    
    PromiseHandler<T> resolve(final Collection<Supplier<T>> suppliers) {
        suppliers.forEach(this.promiseTask::addTask);
        return this;
    }
    
    void async() {
        new PromiseHandlerInvoker<>(executorServiceProvider, promiseTask).async();
    }
    
    Promise<T> await() {
        return new PromiseHandlerInvoker<>(executorServiceProvider, promiseTask).await();
    }
}
