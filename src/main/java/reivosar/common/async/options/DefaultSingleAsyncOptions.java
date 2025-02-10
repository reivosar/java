package reivosar.common.async.options;

import reivosar.common.data.model.Model;
import reivosar.common.lang.ObjectUtil;

class DefaultSingleAsyncOptions extends Model implements SingleAsyncOptions {

    private final Integer retryLimit;
    private final Long timeout;
    private final ErrorHandlingStrategy strategy;

    private DefaultSingleAsyncOptions(final Integer retryLimit,
                                      final Long timeout,
                                      final ErrorHandlingStrategy strategy) {
        this.retryLimit = retryLimit;
        this.timeout = timeout;
        this.strategy = strategy;
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

    static class Builder implements SingleAsyncOptions.Builder {

        private Integer retryLimit;
        private Long timeout;
        private ErrorHandlingStrategy strategy;

        Builder() {
            this.retryLimit = 3;
            this.timeout = 30L;
            this.strategy = ErrorHandlingStrategy.RETRY_AND_CONTINUE;
        }

        @Override
        public Builder retryLimit(int retryLimit) {
            this.retryLimit = ObjectUtil.requireNonNull("retryLimit", retryLimit);
            return this;
        }

        @Override
        public Builder timeout(final Long timeout) {
            this.timeout = ObjectUtil.requireNonNull("timeout", timeout);
            return this;
        }

        @Override
        public Builder errorHandlingStrategy(final ErrorHandlingStrategy strategy) {
            this.strategy = ObjectUtil.requireNonNull("strategy", strategy);
            return this;
        }

        @Override
        public SingleAsyncOptions build() {
            return new DefaultSingleAsyncOptions(this.retryLimit, this.timeout, this.strategy);
        }
    }
}
