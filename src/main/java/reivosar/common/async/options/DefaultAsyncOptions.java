package reivosar.common.async.options;

import reivosar.common.data.model.Model;

import java.util.function.Function;

class DefaultAsyncOptions extends Model implements AsyncOptions {

    private boolean multiple;
    private int multiplicity;
    private int retryLimit;
    private long timeout;
    private ErrorHandlingStrategy strategy;

    private DefaultAsyncOptions(final boolean multiple,
                                final int multiplicity,
                                final int retryLimit,
                                final long timeout,
                                final ErrorHandlingStrategy strategy) {
        this.multiple = multiple;
        this.multiplicity = multiplicity;
        this.retryLimit = retryLimit;
        this.timeout = timeout;
        this.strategy = strategy;
    }

    @Override
    public boolean isMultiple() {
        return this.multiple;
    }

    @Override
    public Integer getMultiplicity() {
        return this.multiplicity;
    }

    @Override
    public Integer getRetryLimit() {
        return this.retryLimit;
    }

    @Override
    public Long getTimeout() {
        return this.timeout;
    }

    @Override
    public ErrorHandlingStrategy getErrorHandlingStrategy() {
        return this.strategy;
    }

    @Override
    public void copyFrom(final AsyncOptions options) {
        if (options != null) {
            this.multiple = options.isMultiple();
            this.retryLimit = options.getRetryLimit();
            this.multiplicity = options.getMultiplicity();
            this.timeout = options.getTimeout();
            this.strategy = options.getErrorHandlingStrategy();
        }
    }

    static class Builder implements AsyncOptions.Builder {

        private boolean multiple;
        private int retryLimit;
        private int multiplicity;
        private long timeout;
        private ErrorHandlingStrategy strategy;

        @Override
        public Builder single(final Function<SingleAsyncOptions.Builder, SingleAsyncOptions.Builder> configurator) {
            final SingleAsyncOptions singleOpts = configurator.apply(new DefaultSingleAsyncOptions.Builder()).build();
            this.multiple = false;
            this.retryLimit = singleOpts.getRetryLimit();
            this.timeout = singleOpts.getTimeout();
            this.strategy = singleOpts.getErrorHandlingStrategy();
            return this;
        }

        @Override
        public Builder multi(final Function<MultiAsyncOptions.Builder, MultiAsyncOptions.Builder> configurator) {
            final MultiAsyncOptions multiOpts = configurator.apply(new DefaultMultiAsyncOptions.Builder()).build();
            this.multiple = true;
            this.multiplicity = multiOpts.getMultiplicity();
            this.retryLimit = multiOpts.getRetryLimit();
            this.timeout = multiOpts.getTimeout();
            this.strategy = multiOpts.getErrorHandlingStrategy();
            return this;
        }

        @Override
        public AsyncOptions build() {
            return new DefaultAsyncOptions(multiple, multiplicity, retryLimit, timeout, strategy);
        }
    }
}
