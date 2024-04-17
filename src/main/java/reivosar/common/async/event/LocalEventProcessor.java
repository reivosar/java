package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventProcessor implements EventProcessor {
    
    static final Singleton<LocalEventProcessor> SINGLETON = new Singleton<>(LocalEventProcessor::new);
    
    @Override
    public void process(final Event event) {
        final EventHandler eventHandler = LocalEventHandlerCache.getLocalEventHandlers(event);
        eventHandler.handle(event);
    }
}
