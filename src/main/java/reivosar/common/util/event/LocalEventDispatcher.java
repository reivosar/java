package reivosar.common.util.event;

import reivosar.common.util.lang.SingletonFactory;

class LocalEventDispatcher implements EventDispatcher {
    
    static final SingletonFactory<LocalEventDispatcher> FACTORY =
            new SingletonFactory<>(() -> new LocalEventDispatcher(LocalEventProcessor.FACTORY.getInstance()));
    
    private final EventProcessor eventProcessor;
    
    private LocalEventDispatcher(final EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }
    
    @Override
    public void dispatch(final Event event) {
        eventProcessor.process(event);
    }
}
