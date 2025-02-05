package reivosar.common.domain.event;

import reivosar.common.async.event.EventDispatcher;
import reivosar.common.lang.Singleton;

public class DomainEventDispatcher<E extends DomainEvent> implements EventDispatcher<E> {

    static final Singleton<DomainEventDispatcher<? extends DomainEvent>> SINGLETON = new Singleton<>(DomainEventDispatcher::new);

    @Override
    public final void dispatch(final E event) {
    }
}
