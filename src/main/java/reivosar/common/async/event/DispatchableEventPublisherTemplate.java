package reivosar.common.async.event;

import reivosar.common.lang.ObjectUtil;

/**
 * An abstract implementation of {@link EventPublisherTemplate} that provides
 * event publishing functionality by dispatching events using an {@link EventDispatcher}.
 * <p>
 * This class defines the {@link #doPublishEvent(Event)} method to publish events
 *
 * @param <E> the type of event to be published, which extends the {@link Event} interface
 * @param <This> the concrete type of the publisher that extends this template
 */
public abstract class DispatchableEventPublisherTemplate<E extends Event, This extends EventPublisher<E, This>>
        extends EventPublisherTemplate<E, This> {

    private final EventDispatcher<E> eventDispatcher;

    /**
     * Creates a new instance of {@code DispatchableEventPublisherTemplate}.
     *
     * @param eventDispatcher the event configuration to be used for publishing events
     * @throws IllegalArgumentException if {@code eventConfig} is null
     */
    protected DispatchableEventPublisherTemplate(final EventDispatcher<E> eventDispatcher) {
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
