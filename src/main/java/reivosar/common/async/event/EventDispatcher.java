package reivosar.common.async.event;

/**
 * Represents a dispatcher responsible for delivering events to all registered listeners.
 * <p>
 * Implementations of this interface handle the logic of notifying listeners
 * about the occurrence of a specific event.
 *
 * @param <E> the type of event to be dispatched, which extends the {@link Event} interface
 */
@FunctionalInterface
public interface EventDispatcher<E extends Event> {

    /**
     * Dispatches the given event to all registered listeners.
     * <p>
     * The implementation determines how the event is delivered to the listeners.
     * This may include synchronous or asynchronous notification, depending on the
     * implementation.
     *
     * @param event the event to dispatch; must not be {@code null}
     * @throws NullPointerException if the event is {@code null}
     */
    void dispatch(E event);
}
