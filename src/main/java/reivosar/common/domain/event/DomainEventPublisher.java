package reivosar.common.domain.event;

import reivosar.common.async.event.DispatchableEventPublisherTemplate;
import reivosar.common.async.event.EventDispatcher;
import reivosar.common.lang.Singleton;

/**
 * A publisher for domain events that ensures they are properly dispatched.
 * <p>
 * This class provides a singleton instance for publishing domain events using a configured {@link EventDispatcher}.
 * It extends {@link DispatchableEventPublisherTemplate}, allowing events to be dispatched asynchronously.
 * </p>
 *
 * @param <E> the type of domain event being published, which extends {@link DomainEvent}
 */
public final class DomainEventPublisher<E extends DomainEvent>
        extends DispatchableEventPublisherTemplate<E, DomainEventPublisher<E>> {

    /**
     * Singleton instance of {@code DomainEventPublisher}.
     * This ensures that only one instance of the publisher is used globally.
     */
    private static final Singleton<DomainEventPublisher<? extends DomainEvent>> SINGLETON =
            new Singleton<>(() -> new DomainEventPublisher<>(DomainEventDispatcher.SINGLETON.getInstance()));

    /**
     * Constructs a {@code DomainEventPublisher} with the given event dispatcher.
     *
     * @param eventDispatcher the event dispatcher responsible for dispatching events
     */
    protected DomainEventPublisher(EventDispatcher<E> eventDispatcher) {
        super(eventDispatcher);
    }

    /**
     * Returns the singleton instance of {@code DomainEventPublisher}.
     * <p>
     * This method provides a globally accessible instance of the event publisher,
     * ensuring that domain events are published consistently.
     * </p>
     *
     * @param <E> the type of domain event
     * @return the singleton instance of {@code DomainEventPublisher}
     */
    @SuppressWarnings("unchecked")
    public static <E extends DomainEvent> DomainEventPublisher<E> instance() {
        return (DomainEventPublisher<E>) SINGLETON.getInstance();
    }
}
