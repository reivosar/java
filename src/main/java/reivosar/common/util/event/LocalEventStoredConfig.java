package reivosar.common.util.event;

import reivosar.common.util.lang.SingletonFactory;

class LocalEventStoredConfig implements EventConfig {
    
    static final SingletonFactory<LocalEventStoredConfig> FACTORY = new SingletonFactory<>(LocalEventStoredConfig::new);
    
    @Override
    public EventDispatcher getEventDispatcher() {
        return LocalEventStoredDispatcher.FACTORY.getInstance();
    }
}
