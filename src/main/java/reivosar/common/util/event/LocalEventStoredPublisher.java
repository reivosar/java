package reivosar.common.util.event;

import reivosar.common.util.lang.SingletonFactory;

class LocalEventStoredPublisher extends AbstractDispatchEventPublisher {
    
    LocalEventStoredPublisher() {
        super(LocalEventStoredConfig.FACTORY.getInstance());
    }
}
