package reivosar.common.util.event;

class LocalDispatchEventPublisher extends AbstractDispatchEventPublisher {
    
    LocalDispatchEventPublisher() {
        super(new LocalEventConfig());
    }
}