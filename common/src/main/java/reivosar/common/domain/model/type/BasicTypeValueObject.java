package reivosar.common.domain.model.type;

import reivosar.common.domain.model.ValueObject;

abstract class BasicTypeValueObject<T> extends ValueObject<BasicTypeValueObject<T>>
{
    protected final T value;

    BasicTypeValueObject(T value) {
        this.value = value;
    }

    BasicTypeValueObject(BasicTypeValueObject<T> vo) {
        this.value = vo.value;
    }
}
