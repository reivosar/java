package reivosar.common.util.promise;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

class PromiseHandler<T> {
    
    private final ExecutorServiceProvider executorServiceProvider;
    private final PromiseTask<T> promiseTask;
    
    PromiseHandler() {
        this(PromiseConfig.DEFAULT_CONFIG);
    }
    
    PromiseHandler(final PromiseConfig promiseConfig) {
        this(promiseConfig.multiple, promiseConfig.timeout);
    }
    
    PromiseHandler(final int multiple, final long timeout) {
        this(Executors.newFixedThreadPool(multiple), timeout);
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
    
    Promise<T> await() {
        return new PromiseHandlerInvoker<>(executorServiceProvider, promiseTask).await();
    }
}
