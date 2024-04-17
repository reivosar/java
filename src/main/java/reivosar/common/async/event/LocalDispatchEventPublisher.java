package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalDispatchEventPublisher extends AbstractDispatchEventPublisher {
    
    static final Singleton<LocalDispatchEventPublisher> SINGLETON = new Singleton<>(LocalDispatchEventPublisher::new);
    
    LocalDispatchEventPublisher() {
        super(LocalEventConfig.SINGLETON.getInstance());
    }
}
