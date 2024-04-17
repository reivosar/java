package reivosar.common.async.event;

class LocalEventStoredPublisher extends AbstractDispatchEventPublisher {
    
    LocalEventStoredPublisher() {
        super(LocalEventStoredConfig.SINGLETON.getInstance());
    }
}
