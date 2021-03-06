package ticketmodeling.domain.model.schedule;

import java.util.Collection;

import reivosar.common.util.model.ValueObject;

public class Schedules extends ValueObject<Schedules>
{
	final Collection<Schedule> schedules;

	public Schedules(final Collection<Schedule> schedules) {
		this.schedules = schedules;
	}
}
