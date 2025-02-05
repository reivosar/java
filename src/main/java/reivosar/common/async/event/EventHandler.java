package reivosar.common.async.event;

/**
 * Represents a handler for processing events.
 * <p>
 * Implementations of this functional interface define the logic to be executed
 * when an event of type {@code E} is handled.
 *
 * @param <E> the type of event to be handled, which extends the {@link Event} interface
 */

abstract class EventHandler<E extends Event> {

    /**
     * Processes the given event.
     * <p>
     * This method contains the logic to handle the event, such as updating state,
     * triggering additional actions, or logging.
     *
     * @param event the event to be handled; must not be {@code null}
     * @throws NullPointerException if the event is {@code null}
     */
    abstract void handle(E event);
}
