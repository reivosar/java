package reivosar.common.async.event;


import reivosar.common.lang.ObjectUtil;

/**
 * An abstract template for an event dispatcher that stores events and triggers event processing.
 * <p>
 * This class manages event storage and ensures that event processing starts when necessary.
 * It interacts with an {@link EventStore} to persist events and an {@link EventProcessingLifecycle}
 * to control event processing.
 *
 * @param <E> the type of event to be dispatched
 */
public abstract class EventStoredDispatcherTemplate<E extends Event> implements EventDispatcher<E> {

    private final EventStore<E> eventStore;
    private final EventProcessingLifecycle eventProcessingLifecycle;

    /**
     * Constructs an {@code EventStoredDispatcherTemplate} with the specified {@link EventStore}
     * and {@link EventProcessingLifecycle}.
     *
     * @param eventStore               the event store where events are persisted
     * @param eventProcessingLifecycle the lifecycle manager for event processing
     */
    public EventStoredDispatcherTemplate(final EventStore<E> eventStore,
                                         final EventProcessingLifecycle eventProcessingLifecycle) {
        this.eventStore = eventStore;
        this.eventProcessingLifecycle = eventProcessingLifecycle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void dispatch(final E event) {
        ObjectUtil.requireNonNull("event", event);
        if (eventStore.append(event) && !eventProcessingLifecycle.isProcessing()) {
            eventProcessingLifecycle.start();
        }
    }
}
