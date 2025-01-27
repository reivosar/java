package reivosar.common.async.event;

/**
 * Represents a processor responsible for handling events.
 * <p>
 * Implementations of this interface define the logic for processing a single event
 * of type {@code E}.
 *
 * @param <E> the type of event to be processed, which extends the {@link Event} interface
 */
public interface EventProcessor<E extends Event> {

    /**
     * Processes the given event.
     * <p>
     * This method contains the logic for processing the event, such as validation,
     * transformation, or triggering actions based on the event's content.
     *
     * @param event the event to be processed; must not be {@code null}
     * @throws NullPointerException if the event is {@code null}
     */
    void process(E event);
}
