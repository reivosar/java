package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventDispatcher implements EventDispatcher {
    
    static final Singleton<LocalEventDispatcher> FACTORY = new Singleton<>(
            () -> new LocalEventDispatcher(LocalEventProcessor.SINGLETON.getInstance())
    );
    
    private final EventProcessor eventProcessor;
    
    private LocalEventDispatcher(final EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }
    
    @Override
    public void dispatch(final Event event) {
        eventProcessor.process(event);
    }
}
