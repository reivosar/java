package reivosar.common.async.event;

/**
 * A functional interface for handling events.
 */
@FunctionalInterface
public interface EventHandler {
    
    /**
     * Handles the given event.
     *
     * @param event the event to be handled
     */
    void handle(Event event);
}
