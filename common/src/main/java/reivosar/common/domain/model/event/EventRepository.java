package reivosar.common.domain.model.event;

import java.util.Collection;

public interface EventRepository
{
	EventId generateId();

	void save(Event event);

	void saveAll(Collection<Event> events);
}
