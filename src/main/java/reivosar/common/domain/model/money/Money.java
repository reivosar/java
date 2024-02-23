package reivosar.common.domain.model.money;

import reivosar.common.domain.model.ValueObject;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
/**
 * A value object representing an amount of money in a specific currency.
 */
public class Money extends ValueObject<Money> {

    private final BigDecimal value;
    private final Currency currency;

    /**
     * Constructs a new Money object with the specified integer value and currency.
     *
     * @param intValue The integer value of the amount of money.
     * @param currency The currency of the money.
     */
    public Money(final int intValue, final Currency currency) {
        this(BigDecimal.valueOf(intValue), currency);
    }

    /**
     * Constructs a new Money object with the specified decimal value and currency.
     *
     * @param decimal  The decimal value of the amount of money.
     * @param currency The currency of the money.
     */
    public Money(final BigDecimal decimal, final Currency currency) {
        this.value = decimal;
        this.currency = currency;
    }

    /**
     * Returns the currency of the money.
     *
     * @return The currency of the money.
     */
    public Currency getCurrency() {
        return currency;
    }

    /**
     * Returns the value of the money.
     *
     * @return The value of the money.
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Adds the specified Money objects to this Money object.
     *
     * @param others The Money objects to add to this one.
     * @return A new Money object representing the sum of this and the specified Money objects.
     */
    public Money plus(Money... others) {
        return new Money(this.value.add(convertMoneyToBigDecimal(others)), currency);
    }

    /**
     * Subtracts the specified Money objects from this Money object.
     *
     * @param others The Money objects to subtract from this one.
     * @return A new Money object representing the difference between this and the specified Money objects.
     */
    public Money minus(Money... others) {
        return new Money(this.value.subtract(convertMoneyToBigDecimal(others)), currency);
    }

    /**
     * Converts the specified Money objects to a BigDecimal value.
     *
     * @param others The Money objects to convert to a BigDecimal.
     * @return A BigDecimal value representing the sum of the specified Money objects.
     */
    private BigDecimal convertMoneyToBigDecimal(Money... others) {
        return Arrays.stream(others)
                .map(v -> v.value)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

