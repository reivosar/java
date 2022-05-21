package reivosar.common.domain.model.event;

import reivosar.common.domain.model.ValueObject;

public class EventTopic extends ValueObject<EventTopic>
{
	final String value;

	public EventTopic(String value) {
		this.value = value;
	}

	public String asString() {
		return value;
	}
}
