package reivosar.common.async.event;

class LocalStoredEventPublisher<E extends Event> extends DispatchEventPublisherTemplate<E> {


    @SuppressWarnings("unchecked")
    LocalStoredEventPublisher() {
        super((EventDispatcher<E>) LocalStoredEventDispatcher.SINGLETON.getInstance());
    }
}
