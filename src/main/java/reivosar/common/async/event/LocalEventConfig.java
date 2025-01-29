package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventConfig<E extends Event> implements EventConfig<E> {

    static final Singleton<LocalEventConfig<?>> SINGLETON = new Singleton<>(LocalEventConfig::new);

    @SuppressWarnings("unchecked")
    @Override
    public EventDispatcher<E> getEventDispatcher() {
        return (EventDispatcher<E>) LocalEventDispatcher.SINGLETON.getInstance();
    }
}
