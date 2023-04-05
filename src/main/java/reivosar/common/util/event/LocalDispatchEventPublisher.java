package reivosar.common.util.event;

import reivosar.common.util.lang.SingletonFactory;

class LocalDispatchEventPublisher extends AbstractDispatchEventPublisher {
    
    static final SingletonFactory<LocalDispatchEventPublisher> FACTORY = new SingletonFactory<>(LocalDispatchEventPublisher::new);
    
    LocalDispatchEventPublisher() {
        super(LocalEventConfig.FACTORY.getInstance());
    }
}
