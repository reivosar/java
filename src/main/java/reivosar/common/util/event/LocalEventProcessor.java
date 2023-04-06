package reivosar.common.util.event;

import reivosar.common.util.lang.Singleton;

class LocalEventProcessor implements EventProcessor {
    
    static final Singleton<LocalEventProcessor> FACTORY = new Singleton<>(LocalEventProcessor::new);
    
    @Override
    public void process(final Event event) {
        final EventHandler eventHandler = LocalEventHandlerCache.getLocalEventHandlers(event);
        eventHandler.handle(event);
    }
}
