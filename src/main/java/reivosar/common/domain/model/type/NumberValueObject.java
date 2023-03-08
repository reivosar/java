package reivosar.common.domain.model.type;

/**
 * Base class for all value objects that represent a number value.
 * Provides basic equality and hash code functionality based on the number value.
 */
public class NumberValueObject extends BasicTypeValueObject<Number>
{
    protected NumberValueObject(final Number value) {
        super(value);
    }

    protected NumberValueObject(final NumberValueObject vo) {
        super(vo);
    }
}
