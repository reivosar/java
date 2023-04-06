package reivosar.common.util.event;

import reivosar.common.util.lang.Singleton;

class LocalEventDispatcher implements EventDispatcher {
    
    static final Singleton<LocalEventDispatcher> FACTORY = new Singleton<>(
            () -> new LocalEventDispatcher(LocalEventProcessor.FACTORY.getInstance())
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
