package reivosar.common.util.event;

class LocalEventStoredPublisher extends AbstractDispatchEventPublisher {
    
    LocalEventStoredPublisher() {
        super(LocalEventStoredConfig.SINGLETON.getInstance());
    }
}
