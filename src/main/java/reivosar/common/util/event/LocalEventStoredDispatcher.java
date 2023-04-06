package reivosar.common.util.event;

import reivosar.common.util.lang.Singleton;

class LocalEventStoredDispatcher implements EventDispatcher {
    
    static final Singleton<LocalEventStoredDispatcher> FACTORY = new Singleton<>(
            () -> new LocalEventStoredDispatcher(
                    InMemoryEventStore.FACTORY.getInstance(),
                    LocalEventProcessor.FACTORY.getInstance()
            )
    );
    
    private final EventStore eventStore;
    private final EventLoop eventLoop;
    
    private LocalEventStoredDispatcher(final EventStore eventStore,
                                       final EventProcessor eventProcessor) {
        this.eventStore = eventStore;
        this.eventLoop = new EventLoop(eventStore, eventProcessor);
    }
    
    @Override
    public void dispatch(final Event event) {
        if (eventStore.create(event)) {
            eventLoop.start();
        }
    }
}
