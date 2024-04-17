package reivosar.common.async.event;

/**
 * An interface for retrieving the configured event dispatcher and event store.
 */
public interface EventConfig {
    
    /**
     * Returns the event dispatcher instance configured for this application.
     *
     * @return the event dispatcher instance
     */
    EventDispatcher getEventDispatcher();
}