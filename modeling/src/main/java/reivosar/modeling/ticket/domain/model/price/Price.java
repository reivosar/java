package reivosar.modeling.ticket.domain.model.price;

import java.math.BigDecimal;
import java.util.Currency;

import reivosar.common.domain.model.money.Money;
import reivosar.common.domain.model.ValueObject;

public class Price extends ValueObject<Price>
{
	final Money money;

	public Price(final Money money) {
		this.money = money;
	}

	public Price(final BigDecimal decimal, final Currency currency) {
		this.money = new Money(decimal, currency);
	}
}
