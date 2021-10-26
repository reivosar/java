package reivosar.common.domain.model.type;

public class NumberValueObject extends TypeValueObject<Number>
{
    public NumberValueObject(Number value) {
        super(value);
    }

    public NumberValueObject(NumberValueObject vo) {
        super(vo);
    }
}
