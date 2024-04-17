package reivosar.common.async.event;

class EventPublisherFactory {
    
    static EventPublisher createDefaultPublisher() {
        return createLocalEventPublisher();
    }
    
    static EventPublisher createLocalEventPublisher() {
        return LocalDispatchEventPublisher.SINGLETON.getInstance();
    }
}
