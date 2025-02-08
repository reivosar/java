package reivosar.common.async.event;

import java.util.function.Function;

class DefaultPublishOptions implements PublishOptions {

    private boolean multiple;
    private int retry;
    private int multiplicity;
    private ErrorHandlingStrategy strategy;

    private DefaultPublishOptions(final boolean multiple,
                                  final int retry,
                                  final int multiplicity,
                                  final ErrorHandlingStrategy strategy) {
        this.multiple = multiple;
        this.retry = retry;
        this.multiplicity = multiplicity;
        this.strategy = strategy;
    }

    @Override
    public boolean isMultiple() {
        return multiple;
    }

    @Override
    public Integer getMultiplicity() {
        return multiplicity;
    }

    @Override
    public Integer getRetry() {
        return retry;
    }

    @Override
    public ErrorHandlingStrategy getErrorHandlingStrategy() {
        return strategy;
    }

    @Override
    public void copyFrom(final PublishOptions options) {
        if (options != null) {
            this.multiple = options.isMultiple();
            this.retry = options.getRetry();
            this.multiplicity = options.getMultiplicity();
            this.strategy = options.getErrorHandlingStrategy();
        }
    }

    static class Builder implements PublishOptions.Builder {

        private boolean multiple = false;
        private int retry = 3;
        private int multiplicity = 1;
        private ErrorHandlingStrategy strategy = ErrorHandlingStrategy.RETRY_AND_CONTINUE;

        @Override
        public Builder single(final Function<SinglePublishOptions, SinglePublishOptions> configurator) {
            SinglePublishOptions singleOpts = new DefaultSinglePublishOptions(retry, strategy);
            singleOpts = configurator.apply(singleOpts);
            this.retry = singleOpts.getRetry();
            return this;
        }

        @Override
        public Builder multi(final Function<MultiPublishOptions, MultiPublishOptions> configurator) {
            MultiPublishOptions multiOpts = new DefaultMultiPublishOptions(multiplicity, strategy);
            multiOpts = configurator.apply(multiOpts);
            this.multiplicity = multiOpts.getMultiplicity();
            this.strategy = multiOpts.getErrorHandlingStrategy();
            this.multiple = true;
            return this;
        }

        @Override
        public PublishOptions build() {
            return new DefaultPublishOptions(multiple, retry, multiplicity, strategy);
        }
    }
}
