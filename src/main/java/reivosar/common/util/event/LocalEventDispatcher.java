package reivosar.common.util.event;

class LocalEventDispatcher implements EventDispatcher {
    
    private final static LocalEventDispatcher EVENT_DISPATCHER;
    
    static {
        EVENT_DISPATCHER = new LocalEventDispatcher(new LocalEventProcessor());
    }
    
    private final EventProcessor eventProcessor;
    
    static LocalEventDispatcher getInstance() {
        return EVENT_DISPATCHER;
    }
    
    private LocalEventDispatcher(final EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }
    
    @Override
    public void dispatch(final Event event) {
        eventProcessor.process(event);
    }
}
