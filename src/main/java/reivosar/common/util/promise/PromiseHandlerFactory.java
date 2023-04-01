package reivosar.common.util.promise;

class PromiseHandlerFactory {
    
    static <T> PromiseHandler<T> create() {
        return new PromiseHandler<>(PromiseConfig.DEFAULT_CONFIG);
    }
    
    static <T> PromiseHandler<T> createWithMultiple(final int multiple) {
        return new PromiseHandler<>(Math.min(multiple, 64));
    }
    
    static <T> PromiseHandler<T> createWithTimeout(final long timeout) {
        return new PromiseHandler<>(PromiseConfig.builder().timeout(timeout).build());
    }
}
