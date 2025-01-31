package reivosar.common.async.event;

class LocalEventStoredPublisher<E extends Event> extends DispatchEventPublisherTemplate<E> {


    @SuppressWarnings("unchecked")
    LocalEventStoredPublisher() {
        super((EventDispatcher<E>) LocalEventStoredDispatcher.SINGLETON.getInstance());
    }
}
