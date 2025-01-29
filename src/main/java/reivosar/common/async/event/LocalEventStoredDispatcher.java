package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventStoredDispatcher<E extends Event> implements EventDispatcher<E> {

    static final Singleton<LocalEventStoredDispatcher<?>> SINGLETON = new Singleton<>(
            () -> new LocalEventStoredDispatcher<>(
                    InMemoryEventStore.SINGLETON.getInstance(),
                    LocalEventProcessor.SINGLETON.getInstance()
            )
    );

    private final EventStore<E> eventStore;
    private final EventLoop<E> eventLoop;

    @SuppressWarnings("unchecked")
    private LocalEventStoredDispatcher(final EventStore<?> eventStore,
                                       final EventProcessor<?> eventProcessor) {
        this.eventStore = (EventStore<E>) eventStore;
        this.eventLoop = new EventLoop<>((EventStore<E>) eventStore, (EventProcessor<E>) eventProcessor);
    }

    @Override
    public void dispatch(final E event) {
        if (eventStore.append(event)) {
            eventLoop.start();
        }
    }
}
