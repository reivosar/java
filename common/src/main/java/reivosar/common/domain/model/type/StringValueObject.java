package reivosar.common.domain.model.type;

/**
 * A value object that represents a string. 
 */
public abstract class StringValueObject extends BasicTypeValueObject<String>
{
	protected StringValueObject(String value) {
        super(value);
    }

	protected StringValueObject(StringValueObject value) {
        super(value);
    }
}