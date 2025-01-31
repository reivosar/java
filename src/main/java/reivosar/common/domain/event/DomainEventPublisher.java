package reivosar.common.domain.event;

import reivosar.common.async.event.DispatchEventPublisherTemplate;
import reivosar.common.async.event.EventDispatcher;
import reivosar.common.lang.Singleton;

public class DomainEventPublisher<E extends DomainEvent> extends DispatchEventPublisherTemplate<E> {

    private static final Singleton<DomainEventPublisher<? extends DomainEvent>> SINGLETON =
            new Singleton<>(() -> new DomainEventPublisher<>(DomainEventDispatcher.SINGLETON.getInstance()));

    protected DomainEventPublisher(EventDispatcher<E> eventDispatcher) {
        super(eventDispatcher);
    }

    @SuppressWarnings("unchecked")
    public static <E extends DomainEvent> DomainEventPublisher<E> instance() {
        return (DomainEventPublisher<E>) SINGLETON.getInstance();
    }
}
