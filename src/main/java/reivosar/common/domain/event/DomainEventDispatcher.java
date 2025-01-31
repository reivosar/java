package reivosar.common.domain.event;

import reivosar.common.async.event.EventDispatcherTemplate;
import reivosar.common.lang.Singleton;

public class DomainEventDispatcher<E extends DomainEvent> extends EventDispatcherTemplate<E> {

    static final Singleton<DomainEventDispatcher<? extends DomainEvent>> SINGLETON = new Singleton<>(DomainEventDispatcher::new);

}
