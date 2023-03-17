package reivosar.common.util.event;

class EventPublisherFactory {
    
    static EventPublisher createDefaultPublisher() {
        return createLocalEventPublisher();
    }
    
    static EventPublisher createLocalEventPublisher() {
        return new LocalEventPublisher();
    }
}
