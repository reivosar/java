package reivosar.common.domain.model.type;

/**
 * A value object that represents a numberg.
 */
public class NumberValueObject extends BasicTypeValueObject<Number>
{
    protected NumberValueObject(Number value) {
        super(value);
    }

    protected NumberValueObject(NumberValueObject vo) {
        super(vo);
    }
}
