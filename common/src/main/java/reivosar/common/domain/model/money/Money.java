package reivosar.common.domain.model.money;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;

import reivosar.common.domain.model.ValueObject;

public class Money extends ValueObject<Money> {
	private final BigDecimal value;
	private final Currency currency;

	public Money(final int intValue, final Currency currency) {
		this(BigDecimal.valueOf(intValue), currency);
	}

	public Money(final BigDecimal decimal, final Currency currency) {
		this.value = decimal;
		this.currency = currency;
	}

	public Money plus(Money... others) {
		return new Money(this.value.add(convertMoneyToBigDecimal(others)), currency);
	}

	public Money minus(Money... others) {
		return new Money(this.value.subtract(convertMoneyToBigDecimal(others)), currency);
	}

	private BigDecimal convertMoneyToBigDecimal(Money... others) {
		return Arrays.asList(others)
				.stream()
				.map(v -> v.value)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
