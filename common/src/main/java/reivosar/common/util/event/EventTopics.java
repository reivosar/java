package reivosar.common.util.event;

import reivosar.common.domain.model.ValueObject;

import java.util.ArrayList;
import java.util.Collection;

public class EventTopics extends ValueObject<EventTopics>
{
	final Collection<String> values;

	public EventTopics() {
        this.values = new ArrayList<>();
	}
}
