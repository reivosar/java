package reivosar.common.domain.model.event;

import java.time.LocalDateTime;

import reivosar.common.util.model.Model;

public abstract class EventTemplate extends Model implements Event
{
	private final EventId eventId;
	private final EventVersion eventVersion;
	private final EventTopic eventTopic;
	private final EventOccurredTime eventOccurredTime;

	protected EventTemplate(EventTopic eventTopic) {
		this(new EventId(), new EventVersion(), eventTopic, new EventOccurredTime());
	}

	protected EventTemplate(
	    final EventId eventId,
	    final EventVersion eventVersion,
		final EventTopic eventTopic,
		final EventOccurredTime eventOccurredTime)
	{
		this.eventId           = eventId;
		this.eventVersion      = eventVersion;
		this.eventTopic        = eventTopic;
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
	public String getEventTopic() {
		return eventTopic.value;
	}

	@Override
	public LocalDateTime getEventOccurredTime() {
		return eventOccurredTime.value.toLocalDateTime();
	}
}
