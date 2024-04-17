package reivosar.common.async.promise;

class MultiPromiseHandlerInvoker<T> extends PromiseHandlerInvoker<T> {
    
    MultiPromiseHandlerInvoker(final ExecutorServiceProvider executorServiceProvider,
                               final PromiseTask<T> promiseTask) {
        super(executorServiceProvider, promiseTask);
    }
    
    @Override
    protected Promise<T> buildPromise(final CompletableFutures<T> futures) {
        return new PromiseBuilder<T>().buildFromMultiPromises(futures);
    }
}

