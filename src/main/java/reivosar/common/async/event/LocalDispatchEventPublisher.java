package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalDispatchEventPublisher<E extends Event> extends DispatchEventPublisherTemplate<E> {

    static final Singleton<LocalDispatchEventPublisher<? extends Event>> SINGLETON = new Singleton<>(LocalDispatchEventPublisher::new);

    @SuppressWarnings("unchecked")
    LocalDispatchEventPublisher() {
        super((EventConfig<E>) LocalEventConfig.SINGLETON.getInstance());
    }
}
