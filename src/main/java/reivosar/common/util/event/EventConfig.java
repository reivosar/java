package reivosar.common.util.event;

/**
 * The EventConfig interface provides a method to obtain an EventDispatcher instance.
 */
public interface EventConfig {
    
    /**
     * Returns an EventDispatcher instance that can be used to dispatch events.
     *
     * @return the EventDispatcher instance
     */
    EventDispatcher getEventDispatcher();
}