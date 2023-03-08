package reivosar.common.domain.model.event;

import java.time.LocalDateTime;

/**
 * An interface representing an event that has occurred in the system.
 */
public interface Event {
    
    /**
     * Gets the unique identifier of the event.
     *
     * @return the event ID
     */
    String getEventId();
    
    /**
     * Gets the version of the event.
     *
     * @return the event version
     */
    int getEventVersion();
    
    /**
     * Gets the topic of the event.
     *
     * @return the event topic
     */
    String getEventTopic();
    
    /**
     * Gets the time when the event occurred.
     *
     * @return the time when the event occurred
     */
    LocalDateTime getEventOccurredTime();
}