package reivosar.common.async.event;

class LocalStoredEventPublisher<E extends Event> extends DispatchableEventPublisherTemplate<E, LocalStoredEventPublisher<E>> {

    @SuppressWarnings("unchecked")
    LocalStoredEventPublisher() {
        super((EventDispatcher<E>) LocalStoredEventDispatcher.SINGLETON.getInstance());
    }
}
