package reivosar.common.domain.model.event;

import reivosar.common.domain.model.Identity;

public class EventId extends Identity<EventId>
{
	final String value;

	public EventId() {
		this.value = genereteNativeIdByUUID();
	}
}
