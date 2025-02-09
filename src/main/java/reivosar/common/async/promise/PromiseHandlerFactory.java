package reivosar.common.async.promise;

import reivosar.common.async.options.AsyncOptions;

class PromiseHandlerFactory {

    static <T> PromiseHandler<T> createPromiseHandler(final AsyncOptions options) {
        return new PromiseHandler<>(options);
    }
}
