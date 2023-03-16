package reivosar.common.util.event;

class LocalEventDispatcher implements EventDispatcher {
    
    @Override
    public void dispatch(final Event event) {
        final LocalEventHandler handler = LocalEventStore.getLocalEventHandlers(event);
        handler.handle(event);
    }
}
