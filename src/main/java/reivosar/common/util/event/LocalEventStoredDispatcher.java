package reivosar.common.util.event;

class LocalEventStoredDispatcher implements EventDispatcher {
    
    private final static LocalEventStoredDispatcher EVENT_DISPATCHER;
    
    static {
        EVENT_DISPATCHER = new LocalEventStoredDispatcher(new InMemoryEventStore(), new LocalEventProcessor());
    }
    
    static LocalEventStoredDispatcher getInstance() {
        return EVENT_DISPATCHER;
    }
    
    private final EventStore eventStore;
    private final EventLoop eventLoop;
    
    private LocalEventStoredDispatcher(final EventStore eventStore,
                                       final EventProcessor eventProcessor) {
        this.eventStore = eventStore;
        this.eventLoop = new EventLoop(eventStore, eventProcessor);
    }
    
    @Override
    public void dispatch(final Event event) {
        if (eventStore.add(event)) {
            eventLoop.start();
        }
    }
}
