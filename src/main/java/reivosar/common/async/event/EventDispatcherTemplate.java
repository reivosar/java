package reivosar.common.async.event;

public abstract class EventDispatcherTemplate<E extends Event> implements EventDispatcher<E> {

    @Override
    public final void dispatch(final E event) {
        final EventHandler<E> eventHandler = LocalEventHandlerCache.getLocalEventHandlers(event);
        eventHandler.handle(event);
    }
}
