package reivosar.common.util.promise;

class PromiseConfig {

    private static final int DEFAULT_MULTIPLE_NUMBER = 1;
    private static final Long DEFAULT_TIMEOUT_SECOND = 30L;

    static final PromiseConfig DEFAULT_CONFIG = new PromiseConfig(DEFAULT_MULTIPLE_NUMBER, DEFAULT_TIMEOUT_SECOND);

    final Integer multiple;
    final Long timeout;

    private PromiseConfig(final Builder builder) {
        this(builder.multiple, builder.timeout);
    }

    private PromiseConfig(final Integer multiple, final Long timeout) {
        this.multiple = multiple != null ? multiple : DEFAULT_MULTIPLE_NUMBER;
        this.timeout = timeout != null ? timeout : DEFAULT_TIMEOUT_SECOND;
    }

    static PromiseConfig.Builder builder() {
        return new PromiseConfig.Builder();
    }

    static class Builder {

        private Integer multiple;
        private Long timeout;

        Builder multiple(final Integer multiple) {
            this.multiple = multiple;
            return this;
        }

        Builder timeout(final Integer timeout) {
            return timeout(Long.valueOf(timeout));
        }

        Builder timeout(final Long timeout) {
            this.timeout = timeout;
            return this;
        }

        PromiseConfig build() {
            return new PromiseConfig(this);
        }
    }
}
