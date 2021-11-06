package reivosar.modeling.ticket.domain.model.screen;

import java.util.Collection;

import reivosar.common.domain.model.ValueObject;

public class Screens extends ValueObject<Screens>
{
	final Collection<Screen> screens;

	public Screens(final Collection<Screen> screens) {
		this.screens = screens;
	}
}
