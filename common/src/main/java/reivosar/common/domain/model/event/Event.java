package reivosar.common.domain.model.event;

import java.time.LocalDateTime;

public interface Event
{
	String getEventId();

	int getEventVersion();

	String getEventTopic();

	LocalDateTime getEventOccurredTime();
}