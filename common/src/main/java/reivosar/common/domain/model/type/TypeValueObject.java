package reivosar.common.domain.model.type;

import reivosar.common.util.model.ValueObject;

public abstract class TypeValueObject<T> extends ValueObject<TypeValueObject<T>>
{
    protected final T value;

    protected TypeValueObject(T value) {
        this.value = value;
    }

    protected TypeValueObject(TypeValueObject<T> vo) {
        this.value = vo.value;
    }
}
