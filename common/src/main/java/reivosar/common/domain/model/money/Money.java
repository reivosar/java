package reivosar.common.domain.model.money;

import java.math.BigDecimal;
import java.util.Currency;

import reivosar.common.util.model.ValueObject;

public class Money extends ValueObject<Money>
{
	final BigDecimal decimal;
	final Currency currency;

	public Money(final int intValue, final Currency currency) {
		this(BigDecimal.valueOf(intValue), currency);
	}

	public Money(final BigDecimal decimal, final Currency currency) {
		this.decimal  = decimal;
		this.currency = currency;
	}
}
