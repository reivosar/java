package reivosar.common.util.promise;

import reivosar.common.util.function.VoidConsumer;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

class PromiseHandler<T> {
    
    private final ExecutorServiceProvider executorServiceProvider;
    private final PromiseTask<T> promiseTask;
    
    PromiseHandler(final PromiseConfig promiseConfig) {
        this(promiseConfig.multiple, promiseConfig.timeout);
    }
    
    PromiseHandler(final int multiple) {
        this(Executors.newFixedThreadPool(multiple), PromiseConfig.DEFAULT_TIMEOUT_SECOND);
    }
    
    private PromiseHandler(final int multiple, final long timeout) {
        this(Executors.newFixedThreadPool(multiple), timeout);
    }
    
    private PromiseHandler(final ExecutorService executorService, final long timeout) {
        this.executorServiceProvider = new ExecutorServiceProvider(executorService, timeout);
        this.promiseTask = new PromiseTask<>();
    }
    
    PromiseHandler<T> withSupplier(final Supplier<T> supplier) {
        return withSuppliers(List.of(supplier));
    }
    
    PromiseHandler<T> withSuppliers(final Collection<Supplier<T>> suppliers) {
        suppliers.forEach(this.promiseTask::addTask);
        return this;
    }
    
    PromiseHandler<T> withVoidConsumers(final Collection<VoidConsumer> voidConsumers) {
        voidConsumers.forEach(voidConsumer -> this.promiseTask.addTask(() -> {
            voidConsumer.accept();
            return null;
        }));
        return this;
    }
    
    Promise<T> handle() {
        return PromiseHandlerInvokerFactory.create(executorServiceProvider, promiseTask).invoke();
    }
}
