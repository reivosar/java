package reivosar.modeling.ticket.domain.model.schedule;

import java.util.Collection;

import reivosar.common.domain.model.ValueObject;

public class Schedules extends ValueObject<Schedule>
{
	final Collection<Schedule> values;

	public Schedules(final Collection<Schedule> values) {
		this.values = values;
	}
}
