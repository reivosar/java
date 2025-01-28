package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventProcessor<E extends Event> implements EventProcessor<E> {

    static final Singleton<LocalEventProcessor<? extends Event>> SINGLETON = new Singleton<>(LocalEventProcessor::new);
    
    @Override
    public void process(final E event) {
        final EventHandler<E> eventHandler = LocalEventHandlerCache.getLocalEventHandlers(event);
        eventHandler.handle(event);
    }
}
