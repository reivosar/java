package reivosar.common.async.event;

class DefaultSinglePublishOptions implements SinglePublishOptions {

    private int retry;
    private ErrorHandlingStrategy strategy;

    DefaultSinglePublishOptions(final int retry,
                                final ErrorHandlingStrategy strategy) {
        this.retry = retry;
        this.strategy = strategy;
    }

    @Override
    public SinglePublishOptions retry(final int retry) {
        this.retry = retry;
        return this;
    }

    @Override
    public int getRetry() {
        return this.retry;
    }

    @Override
    public SinglePublishOptions errorHandlingStrategy(final ErrorHandlingStrategy strategy) {
        this.strategy = strategy;
        return this;
    }

    @Override
    public ErrorHandlingStrategy getErrorHandlingStrategy() {
        return this.strategy;
    }
}
