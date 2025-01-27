package reivosar.common.async.event;

/**
 * Represents the configuration for managing events in the application.
 * <p>
 * This interface provides access to the {@link EventDispatcher}, which is responsible
 * for dispatching events of type {@code E}.
 *
 * @param <E> the type of event to be managed, which extends the {@link Event} interface
 */
public interface EventConfig<E extends Event> {

    /**
     * Retrieves the {@link EventDispatcher} configured for handling events of type {@code E}.
     *
     * @return the configured {@link EventDispatcher} instance
     * @throws IllegalStateException if the dispatcher is not properly configured
     */
    EventDispatcher<E> getEventDispatcher();
}
