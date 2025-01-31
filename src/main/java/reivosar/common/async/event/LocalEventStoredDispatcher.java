package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventStoredDispatcher<E extends Event> extends EventStoredDispatcherTemplate<E> {

    static final Singleton<LocalEventStoredDispatcher<? extends Event>> SINGLETON = new Singleton<>(() -> new LocalEventStoredDispatcher<>(InMemoryEventStore.SINGLETON.getInstance(), EventStoredProcessorWorker.SINGLETON.getInstance()));

    @SuppressWarnings("unchecked")
    private LocalEventStoredDispatcher(final EventStore<?> eventStore,
                                       final EventProcessingLifecycle eventProcessingLifecycle) {
        super((EventStore<E>) eventStore, eventProcessingLifecycle);
    }
}
