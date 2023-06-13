package reivosar.common.util.event;

import reivosar.common.util.lang.Singleton;

class LocalDispatchEventPublisher extends AbstractDispatchEventPublisher {
    
    static final Singleton<LocalDispatchEventPublisher> SINGLETON = new Singleton<>(LocalDispatchEventPublisher::new);
    
    LocalDispatchEventPublisher() {
        super(LocalEventConfig.SINGLETON.getInstance());
    }
}
