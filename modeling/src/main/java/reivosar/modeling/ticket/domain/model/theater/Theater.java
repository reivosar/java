package reivosar.modeling.ticket.domain.model.theater;

import java.util.Collection;

import reivosar.common.domain.model.Entity;
import reivosar.modeling.ticket.domain.model.screen.ScreenId;

public class Theater extends Entity<TheaterId, Theater>
{
	final TheaterId id;
	final TheaterProfile profile;
	final Collection<ScreenId> screens;

	public Theater(
		final TheaterId id,
		final TheaterProfile profile,
		final Collection<ScreenId> screens)
	{
		this.id      = id;
		this.profile = profile;
		this.screens = screens;
	}

	public Collection<ScreenId> allScreening() {
		return screens;
	}

	@Override
	public TheaterId identity() {
		return id;
	}
}
