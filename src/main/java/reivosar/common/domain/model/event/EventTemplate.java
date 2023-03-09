package reivosar.common.domain.model.event;

import java.time.LocalDateTime;

import reivosar.common.util.model.Model;

/**
 * A base implementation of the {@link Event} interface that provides default implementations
 * for its methods.
 */
public abstract class EventTemplate extends Model implements Event {
    
    /**
     * The unique identifier of the event.
     */
    private final EventId eventId;
    
    /**
     * The version of the event.
     */
    private final EventVersion eventVersion;
    
    /**
     * The topic of the event.
     */
    private final EventTopic eventTopic;
    
    /**
     * The time the event occurred.
     */
    private final EventOccurredTime eventOccurredTime;
    
    /**
     * Constructs a new instance of the {@code EventTemplate} class with a generated {@code eventId},
     * default {@code eventVersion}, default {@code eventOccurredTime}, and the specified {@code eventTopic}.
     *
     * @param eventTopic The topic of the event.
     */
    protected EventTemplate(EventTopic eventTopic) {
        this(new EventId(), new EventVersion(), eventTopic, new EventOccurredTime());
    }
    
    /**
     * Constructs a new instance of the {@code EventTemplate} class with the specified parameters.
     *
     * @param eventId           The unique identifier of the event.
     * @param eventVersion      The version of the event.
     * @param eventTopic        The topic of the event.
     * @param eventOccurredTime The time the event occurred.
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
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getEventId() {
        return eventId.value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getEventVersion() {
        return eventVersion.value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String getEventTopic() {
        return eventTopic.value;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime getEventOccurredTime() {
        return eventOccurredTime.value.toLocalDateTime();
    }
}