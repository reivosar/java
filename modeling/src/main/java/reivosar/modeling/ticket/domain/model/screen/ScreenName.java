package reivosar.modeling.ticket.domain.model.screen;

import reivosar.common.domain.model.ValueObject;

public class ScreenName extends ValueObject<ScreenName>
{
	final String value;

	public ScreenName(final String value) {
		this.value = value;
	}
}
