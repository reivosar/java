package reivosar.common.util.promise;

class PromiseHandlerFactory {
    
    static <T> PromiseHandler<T> createWithDefaultSettings() {
        return PromiseHandler.createNormalThreadPromiseHandler(PromiseConfig.DEFAULT_CONFIG);
    }
    
    static <T> PromiseHandler<T> createWithTimeout(final long timeout) {
        return PromiseHandler.createNormalThreadPromiseHandler(
                PromiseConfig.builder().timeout(timeout).build());
    }
    
    static <T> PromiseHandler<T> createMultiplePromiseHandler(final long timeout) {
        return PromiseHandler.createVirtualThreadPromiseHandler(
                PromiseConfig.builder().timeout(timeout).build());
    }
}
