package reivosar.common.util.event;

import reivosar.common.util.lang.SingletonFactory;

class LocalEventConfig implements EventConfig {
    
    static final SingletonFactory<LocalEventConfig> FACTORY = new SingletonFactory<>(LocalEventConfig::new);
    
    @Override
    public EventDispatcher getEventDispatcher() {
        return LocalEventDispatcher.FACTORY.getInstance();
    }
}
