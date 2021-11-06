package reivosar.modeling.ticket.domain.model.screen;

import reivosar.common.domain.model.Identity;

public class ScreenId extends Identity<ScreenId>
{
	final String value;

	public ScreenId(final String value) {
		this.value = value;
	}
}
