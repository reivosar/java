package reivosar.common.util.event;

import java.time.LocalDateTime;
import java.util.Collection;

import reivosar.common.util.model.Model;

public abstract class EventTemplate extends Model implements Event
{
	private final EventId eventId;
	private final EventVersion eventVersion;
	private final EventOccurredTime eventOccurredTime;

	protected EventTemplate() {
		this(new EventId(), new EventVersion(), new EventOccurredTime());
	}

	protected EventTemplate(
	    final EventId eventId,
	    final EventVersion eventVersion,
		final EventOccurredTime eventOccurredTime)
	{
		this.eventId           = eventId;
		this.eventVersion      = eventVersion;
		this.eventOccurredTime = eventOccurredTime;
	}

	@Override
	public String getEventId() {
		return eventId.value;
	}

	@Override
	public int getEventVersion() {
		return eventVersion.value;
	}

	@Override
	public LocalDateTime getEventOccurredTime() {
		return eventOccurredTime.value.toLocalDateTime();
	}
}
