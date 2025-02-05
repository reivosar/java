package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventDispatcher<E extends Event> implements EventDispatcher<E> {

    static final Singleton<LocalEventDispatcher<? extends Event>> SINGLETON = new Singleton<>(LocalEventDispatcher::new);

    @Override
    public final void dispatch(final E event) {
        final EventHandler<E> eventHandler = LocalEventHandlerCache.getLocalEventHandlers(event);
        eventHandler.handle(event);
    }
}
