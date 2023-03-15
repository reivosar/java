package reivosar.common.util.event;

/**
 * Defines an interface for an event dispatcher, responsible for dispatching an event to all registered listeners.
 */
@FunctionalInterface
public interface EventDispatcher {
    
    /**
     * Dispatches the given event to all registered listeners.
     *
     * @param event the event to dispatch
     */
    void dispatch(Event event);
}