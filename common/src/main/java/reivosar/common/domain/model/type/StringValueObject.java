package reivosar.common.domain.model.type;

public abstract class StringValueObject extends TypeValueObject<String>
{
	protected StringValueObject(String value) {
        super(value);
    }

	protected StringValueObject(StringValueObject value) {
        super(value);
    }
}