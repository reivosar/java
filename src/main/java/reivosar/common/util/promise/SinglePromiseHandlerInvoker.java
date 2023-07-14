package reivosar.common.util.promise;

class SinglePromiseHandlerInvoker<T> extends PromiseHandlerInvoker<T> {
    
    SinglePromiseHandlerInvoker(final ExecutorServiceProvider executorServiceProvider,
                                final PromiseTask<T> promiseTask) {
        super(executorServiceProvider, promiseTask);
    }
    
    @Override
    protected Promise<T> buildPromise(final CompletableFutures<T> futures) {
        return new PromiseBuilder<T>().buildFromSinglePromise(futures);
    }
}

