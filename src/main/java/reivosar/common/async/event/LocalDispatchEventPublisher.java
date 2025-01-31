package reivosar.common.async.event;

class LocalDispatchEventPublisher<E extends Event> extends DispatchEventPublisherTemplate<E> {

    @SuppressWarnings("unchecked")
    LocalDispatchEventPublisher() {
        super((EventDispatcher<E>) LocalEventDispatcher.SINGLETON.getInstance());
    }
}
