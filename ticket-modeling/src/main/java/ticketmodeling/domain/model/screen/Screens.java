package ticketmodeling.domain.model.screen;

import java.util.Collection;

import reivosar.common.util.model.ValueObject;

public class Screens extends ValueObject<Screens>
{
	final Collection<Screen> screens;

	public Screens(final Collection<Screen> screens) {
		this.screens = screens;
	}
}
