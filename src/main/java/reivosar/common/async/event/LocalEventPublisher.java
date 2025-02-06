package reivosar.common.async.event;

class LocalEventPublisher<E extends Event>
        extends DispatchableEventPublisherTemplate<E, LocalEventPublisher<E>> {

    @SuppressWarnings("unchecked")
    LocalEventPublisher() {
        super((EventDispatcher<E>) LocalEventDispatcher.SINGLETON.getInstance());
    }
}
