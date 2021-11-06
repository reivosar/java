package reivosar.modeling.ticket.domain.model.screen;

import java.util.Collection;

import reivosar.common.domain.model.ValueObject;

public class Screens extends ValueObject<Screen>
{
	final Collection<Screen> values;

	public Screens(final Collection<Screen> values) {
		this.values = values;
	}
}
