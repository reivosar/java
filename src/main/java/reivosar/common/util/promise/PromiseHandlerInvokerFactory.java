package reivosar.common.util.promise;

class PromiseHandlerInvokerFactory {
    
    static <T> PromiseHandlerInvoker<T> create(final ExecutorServiceProvider executorServiceProvider,
                                               final PromiseTask<T> promiseTask) {
        if (promiseTask.supplierCount() > 1) {
            return new MultiPromiseHandlerInvoker<>(executorServiceProvider, promiseTask);
        }
        return new SinglePromiseHandlerInvoker<>(executorServiceProvider, promiseTask);
    }
}
