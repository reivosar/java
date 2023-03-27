package reivosar.common.util.event;

/**
 * An interface for processing events.
 */
public interface EventProcessor {
    
    /**
     * Processes a single event.
     *
     * @param event the event to be processed
     */
    void process(Event event);
}
