package reivosar.common.util.event;

class LocalEventPublisher extends AbstractEventPublisher {
    
    LocalEventPublisher() {
        super(new LocalEventConfig());
    }
}
