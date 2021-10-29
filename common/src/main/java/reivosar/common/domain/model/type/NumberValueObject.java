package reivosar.common.domain.model.type;

/**
 * A value object that represents a numberg.
 */
public class NumberValueObject extends BasicTypeValueObject<Number>
{
    public NumberValueObject(Number value) {
        super(value);
    }

    public NumberValueObject(NumberValueObject vo) {
        super(vo);
    }
}
