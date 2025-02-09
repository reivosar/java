package reivosar.common.async.promise;

import reivosar.common.async.options.AsyncOptions;
import reivosar.common.lang.function.VoidConsumer;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

class PromiseHandler<T> {

    private final ExecutorServiceProvider executorServiceProvider;
    private final PromiseTask<T> promiseTask;

    PromiseHandler(final AsyncOptions options) {
        this.executorServiceProvider = new ExecutorServiceProvider(getExecutorService(options), options.getTimeout());
        this.promiseTask = new PromiseTask<>();
    }

    private ExecutorService getExecutorService(final AsyncOptions options) {
        if (!options.isMultiple()) {
            return Executors.newSingleThreadExecutor();
        }
        int multiplicity = options.getMultiplicity();
        return (multiplicity > 0)
                ? Executors.newFixedThreadPool(multiplicity)
                : Executors.newVirtualThreadPerTaskExecutor();
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
