package reivosar.common.util.promise;

import reivosar.common.util.lang.ObjectUtil;

import java.util.Collection;
import java.util.function.Supplier;

class PromiseHandlerFactory {
    
    static <T> PromiseHandler<T> create() {
        return new PromiseHandler<>(PromiseConfig.DEFAULT_CONFIG);
    }
    
    static <T> PromiseHandler<T> createWithSuppliers(final Collection<Supplier<Void>> suppliers) {
        ObjectUtil.requireNonNullAndEmpty("suppliers", suppliers);
        return createWithMultiple(suppliers.size());
    }
    
    static <T> PromiseHandler<T> createWithMultiple(final int multiple) {
        return new PromiseHandler<>(multiple);
    }
    
    static <T> PromiseHandler<T> createWithTimeout(final long timeout) {
        return new PromiseHandler<>(PromiseConfig.builder().timeout(timeout).build());
    }
}
