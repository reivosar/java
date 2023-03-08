package reivosar.common.domain.model.event;

import java.time.LocalDateTime;

import reivosar.common.util.model.Model;

/**
 * An abstract class representing an event template that implements the {@link Event} interface.
 */
public abstract class EventTemplate extends Model implements Event {
    
    private final EventId eventId;
    private final EventVersion eventVersion;
    private final EventTopic eventTopic;
    private final EventOccurredTime eventOccurredTime;
    
    /**
     * Creates a new event template with a generated ID, version 1, the specified topic, and the current time as the
     * occurred time.
     *
     * @param eventTopic the topic of the event
     */
    protected EventTemplate(EventTopic eventTopic) {
        this(new EventId(), new EventVersion(), eventTopic, new EventOccurredTime());
    }
    
    /**
     * Creates a new event template with the specified ID, version, topic, and occurred time.
     *
     * @param eventId           the ID of the event
     * @param eventVersion      the version of the event
     * @param eventTopic        the topic of the event
     * @param eventOccurredTime the occurred time of the event
     */
    protected EventTemplate(
            final EventId eventId,
            final EventVersion eventVersion,
            final EventTopic eventTopic,
            final EventOccurredTime eventOccurredTime) {
        this.eventId = eventId;
        this.eventVersion = eventVersion;
        this.eventTopic = eventTopic;
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
