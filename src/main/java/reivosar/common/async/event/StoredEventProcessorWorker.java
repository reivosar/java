package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class StoredEventProcessorWorker<E extends Event> extends StoredEventProcessorWorkerTemplate<E> {
    static final Singleton<StoredEventProcessorWorker<? extends Event>> SINGLETON = new Singleton<>(
            () -> new StoredEventProcessorWorker<>(
                    InMemoryEventStore.SINGLETON.getInstance()));

    @SuppressWarnings("unchecked")
    private StoredEventProcessorWorker(final EventStore<? extends Event> eventStore) {
        super((EventStore<E>) eventStore);
    }
}
