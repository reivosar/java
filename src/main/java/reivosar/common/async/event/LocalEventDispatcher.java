package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventDispatcher<E extends Event> extends EventDispatcherTemplate<E> {

    static final Singleton<LocalEventDispatcher<? extends Event>> SINGLETON = new Singleton<>(LocalEventDispatcher::new);

    protected LocalEventDispatcher() {
        super();
    }
}
