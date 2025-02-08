package reivosar.common.async.event;

import reivosar.common.lang.ObjectUtil;

class DefaultSinglePublishOptions implements SinglePublishOptions {

    private Integer retry;
    private ErrorHandlingStrategy strategy;

    DefaultSinglePublishOptions(final Integer retry,
                                final ErrorHandlingStrategy strategy) {
        this.retry = ObjectUtil.requireNonNull("retry", retry);
        this.strategy = ObjectUtil.requireNonNull("strategy", strategy);
    }

    @Override
    public SinglePublishOptions retry(final Integer retry) {
        this.retry = ObjectUtil.requireNonNull("retry", retry);
        return this;
    }

    @Override
    public Integer getRetry() {
        return this.retry;
    }

    @Override
    public SinglePublishOptions errorHandlingStrategy(final ErrorHandlingStrategy strategy) {
        this.strategy = ObjectUtil.requireNonNull("strategy", strategy);
        return this;
    }

    @Override
    public ErrorHandlingStrategy getErrorHandlingStrategy() {
        return this.strategy;
    }
}
