package reivosar.common.async.event;

import reivosar.common.lang.ObjectUtil;

/**
 * An abstract implementation of {@link EventPublisherTemplate} that provides
 * event publishing functionality by dispatching events using an {@link EventDispatcher}.
 * <p>
 * This class defines the {@link #doPublishEvent(Event)} method to publish events
 *
 * @param <E> the type of event to be published, which extends the {@link Event} interface
 */
public abstract class DispatchEventPublisherTemplate<E extends Event> extends EventPublisherTemplate<E> {

    private final EventDispatcher<E> eventDispatcher;

    /**
     * Creates a new instance of {@code DispatchEventPublisherTemplate}.
     *
     * @param eventDispatcher the event configuration to be used for publishing events
     * @throws IllegalArgumentException if {@code eventConfig} is null
     */
    protected DispatchEventPublisherTemplate(final EventDispatcher<E> eventDispatcher) {
        this.eventDispatcher = ObjectUtil.requireNonNull("eventDispatcher", eventDispatcher);
    }

    /**
     * Publishes a single event by dispatching it using the {@link EventDispatcher}.
     *
     * @param event the event to be dispatched
     * @throws NullPointerException if {@code event} is null
     */
    @Override
    protected final void doPublishEvent(final E event) {
        this.eventDispatcher.dispatch(ObjectUtil.requireNonNull("event", event));
    }
}
