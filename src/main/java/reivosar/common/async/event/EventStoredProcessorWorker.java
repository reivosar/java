package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class EventStoredProcessorWorker<E extends Event> extends EventStoredProcessorWorkerTemplate<E> {

    static final Singleton<EventStoredProcessorWorker<? extends Event>> SINGLETON = new Singleton<>(
            () -> new EventStoredProcessorWorker<>(
                    InMemoryEventStore.SINGLETON.getInstance(),
                    LocalEventProcessor.SINGLETON.getInstance()));

    @SuppressWarnings("unchecked")
    private EventStoredProcessorWorker(final EventStore<? extends Event> eventStore,
                                       final EventProcessor<? extends Event> eventProcessor) {
        super((EventStore<E>) eventStore, (EventProcessor<E>) eventProcessor);
    }
}
