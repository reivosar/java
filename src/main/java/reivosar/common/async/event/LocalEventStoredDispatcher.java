package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventStoredDispatcher implements EventDispatcher {
    
    static final Singleton<LocalEventStoredDispatcher> SINGLETON = new Singleton<>(
            () -> new LocalEventStoredDispatcher(
                    InMemoryEventStore.SINGLETON.getInstance(),
                    LocalEventProcessor.SINGLETON.getInstance()
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
