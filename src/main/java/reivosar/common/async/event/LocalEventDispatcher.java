package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventDispatcher<E extends Event> implements EventDispatcher<E> {

    static final Singleton<LocalEventDispatcher<? extends Event>> FACTORY = new Singleton<>(
            () -> new LocalEventDispatcher<>(LocalEventProcessor.SINGLETON.getInstance())
    );

    private final EventProcessor<E> eventProcessor;

    private LocalEventDispatcher(final EventProcessor<E> eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void dispatch(final E event) {
        eventProcessor.process(event);
    }
}
