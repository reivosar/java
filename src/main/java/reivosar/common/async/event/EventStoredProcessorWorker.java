package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class EventStoredProcessorWorker<E extends Event> extends EventStoredProcessorWorkerTemplate<E> {

    static final Singleton<EventStoredProcessorWorker<? extends Event>> SINGLETON = new Singleton<>(
            () -> new EventStoredProcessorWorker<>(
                    InMemoryEventStore.SINGLETON.getInstance()));

    @SuppressWarnings("unchecked")
    private EventStoredProcessorWorker(final EventStore<? extends Event> eventStore) {
        super((EventStore<E>) eventStore);
    }
}
