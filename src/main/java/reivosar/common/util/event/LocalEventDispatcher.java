package reivosar.common.util.event;

class LocalEventDispatcher implements EventDispatcher {
    
    @Override
    public void dispatch(final Event event) {
        final EventHandler eventHandler = LocalEventHandlerCache.getLocalEventHandlers(event);
        eventHandler.handle(event);
    }
}
