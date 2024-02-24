package reivosar.common.domain.model.type;

import reivosar.common.domain.model.ValueObject;
import reivosar.common.util.lang.ObjectUtil;

abstract class BasicTypeValueObject<T> extends ValueObject<BasicTypeValueObject<T>> {
    protected final T value;
    
    BasicTypeValueObject(final T value) {
        this.value = ObjectUtil.requireNonNull("value", value);
    }
    
    BasicTypeValueObject(final BasicTypeValueObject<T> vo) {
        ObjectUtil.requireNonNull("vo", vo);
        this.value = vo.value;
    }
}
