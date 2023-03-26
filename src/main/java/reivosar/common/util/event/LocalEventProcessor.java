package reivosar.common.util.event;

class LocalEventProcessor implements EventProcessor {
    
    @Override
    public void process(final Event event) {
        final EventHandler eventHandler = LocalEventHandlerCache.getLocalEventHandlers(event);
        eventHandler.handle(event);
    }
}
