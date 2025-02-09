package reivosar.common.async.options;

import reivosar.common.data.model.Model;
import reivosar.common.lang.ObjectUtil;

class DefaultSingleAsyncOptions extends Model implements SingleAsyncOptions {

    private Integer retryLimit;
    private Long timeout;
    private ErrorHandlingStrategy strategy;

    DefaultSingleAsyncOptions() {
        this.retryLimit = 3;
        this.timeout = 30L;
        this.strategy = ErrorHandlingStrategy.RETRY_AND_CONTINUE;
    }

    @Override
    public SingleAsyncOptions retryLimit(int retryLimit) {
        this.retryLimit = ObjectUtil.requireNonNull("retryLimit", retryLimit);
        return this;
    }

    @Override
    public Integer getRetryLimit() {
        return this.retryLimit;
    }

    @Override
    public SingleAsyncOptions timeout(final Long timeout) {
        this.timeout = ObjectUtil.requireNonNull("timeout", timeout);
        return this;
    }

    @Override
    public Long getTimeout() {
        return this.timeout;
    }

    @Override
    public SingleAsyncOptions errorHandlingStrategy(final ErrorHandlingStrategy strategy) {
        this.strategy = ObjectUtil.requireNonNull("strategy", strategy);
        return this;
    }

    @Override
    public ErrorHandlingStrategy getErrorHandlingStrategy() {
        return this.strategy;
    }
}
