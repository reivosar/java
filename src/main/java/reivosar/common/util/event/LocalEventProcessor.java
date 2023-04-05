package reivosar.common.util.event;

import reivosar.common.util.lang.SingletonFactory;

class LocalEventProcessor implements EventProcessor {
    
    static final SingletonFactory<LocalEventProcessor> FACTORY = new SingletonFactory<>(LocalEventProcessor::new);
    
    @Override
    public void process(final Event event) {
        final EventHandler eventHandler = LocalEventHandlerCache.getLocalEventHandlers(event);
        eventHandler.handle(event);
    }
}
