package reivosar.common.domain.model.event;


import reivosar.common.domain.model.ValueObject;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class EventStore extends ValueObject<EventStore>
{
	private final Collection<Event> events;

	public EventStore() {
		this.events = new ConcurrentLinkedQueue<>();
	}

	public void eventOccurred(Event...domainEvents) {
		this.events.addAll(Arrays.asList(domainEvents));
	}

	public Collection<Event> allEvents() {
		return Collections.unmodifiableCollection(events);
	}

	public Collection<Event> allEvents(EventVersion eventVersion) {
		return allEvents().stream()
			.filter  (event -> eventVersion.value.equals(event.getEventVersion()))
			.collect (Collectors.toUnmodifiableList());
	}

	public void clear() {
        this.events.clear();
    }

    public boolean hasEvents() {
        return !events.isEmpty();
    }
}
