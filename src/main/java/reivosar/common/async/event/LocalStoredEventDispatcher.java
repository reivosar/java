package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalStoredEventDispatcher<E extends Event> extends StoredEventDispatcherTemplate<E> {

    static final Singleton<LocalStoredEventDispatcher<? extends Event>> SINGLETON =
            new Singleton<>(() -> new LocalStoredEventDispatcher<>(
                    InMemoryEventStore.SINGLETON.getInstance(),
                    StoredEventProcessorWorker.SINGLETON.getInstance()));

    @SuppressWarnings("unchecked")
    private LocalStoredEventDispatcher(final EventStore<?> eventStore,
                                       final EventProcessingLifecycle eventProcessingLifecycle) {
        super((EventStore<E>) eventStore, eventProcessingLifecycle);
    }
}
