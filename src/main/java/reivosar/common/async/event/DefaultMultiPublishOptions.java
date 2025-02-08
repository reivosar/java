package reivosar.common.async.event;

import reivosar.common.lang.ObjectUtil;

class DefaultMultiPublishOptions implements MultiPublishOptions {

    private Integer multiplicity;
    private ErrorHandlingStrategy strategy;

    DefaultMultiPublishOptions(final Integer multiplicity, final ErrorHandlingStrategy strategy) {
        this.multiplicity = ObjectUtil.requireNonNull("multiplicity", multiplicity);
        this.strategy = ObjectUtil.requireNonNull("strategy", strategy);
    }

    @Override
    public MultiPublishOptions multiplicity(final Integer multiplicity) {
        this.multiplicity = ObjectUtil.requireNonNull("multiplicity", multiplicity);
        return this;
    }

    @Override
    public MultiPublishOptions errorHandlingStrategy(final ErrorHandlingStrategy strategy) {
        this.strategy = ObjectUtil.requireNonNull("strategy", strategy);
        return this;
    }

    @Override
    public Integer getMultiplicity() {
        return this.multiplicity;
    }

    @Override
    public ErrorHandlingStrategy getErrorHandlingStrategy() {
        return this.strategy;
    }
}
