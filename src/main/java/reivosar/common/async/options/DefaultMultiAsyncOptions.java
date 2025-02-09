package reivosar.common.async.options;

import reivosar.common.data.model.Model;
import reivosar.common.lang.ObjectUtil;

class DefaultMultiAsyncOptions extends Model implements MultiAsyncOptions {

    private Integer multiplicity;
    private Integer retryLimit;
    private Long timeout;
    private ErrorHandlingStrategy strategy;

    DefaultMultiAsyncOptions() {
        this.multiplicity = -1;
        this.retryLimit = 3;
        this.timeout = 30L;
        this.strategy = ErrorHandlingStrategy.RETRY_AND_CONTINUE;
    }

    @Override
    public MultiAsyncOptions multiplicity(final Integer multiplicity) {
        this.multiplicity = ObjectUtil.requireNonNull("multiplicity", multiplicity);
        return this;
    }

    @Override
    public MultiAsyncOptions retryLimit(final Integer retryLimit) {
        this.retryLimit = ObjectUtil.requireNonNull("retryLimit", retryLimit);
        return this;
    }

    @Override
    public MultiAsyncOptions timeout(final Long timeout) {
        this.timeout = ObjectUtil.requireNonNull("timeout", timeout);
        return this;
    }

    @Override
    public MultiAsyncOptions errorHandlingStrategy(final ErrorHandlingStrategy strategy) {
        this.strategy = ObjectUtil.requireNonNull("strategy", strategy);
        return this;
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
}
