package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventStoredConfig<E extends Event> implements EventConfig<E> {

    static final Singleton<LocalEventStoredConfig<? extends Event>> SINGLETON = new Singleton<>(LocalEventStoredConfig::new);

    @SuppressWarnings("unchecked")
    @Override
    public EventDispatcher<E> getEventDispatcher() {
        return (EventDispatcher<E>) LocalEventStoredDispatcher.SINGLETON.getInstance();
    }
}
