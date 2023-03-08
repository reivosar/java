package reivosar.common.domain.model.event;

import reivosar.common.domain.model.ValueObject;

/**
 * A value object representing the topic of an event.
 */
public class EventTopic extends ValueObject<EventTopic> {
    
    /**
     * The topic value.
     */
    final String value;
    
    /**
     * Constructs an event topic with the specified value.
     *
     * @param value the topic value
     */
    public EventTopic(String value) {
        this.value = value;
    }
    
    /**
     * Returns the topic value as a string.
     *
     * @return the topic value
     */
    public String asString() {
        return value;
    }
}
