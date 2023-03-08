package reivosar.common.domain.model.type;

/**
 * Base class for all value objects that represent a string value.
 * Provides basic equality and hash code functionality based on the string value.
 */
public abstract class StringValueObject extends BasicTypeValueObject<String> {
    
    protected StringValueObject(final String value) {
        super(value);
    }
    
    protected StringValueObject(final StringValueObject value) {
        super(value);
    }
}
