package reivosar.common.util.event;

import reivosar.common.util.lang.Singleton;

class LocalEventStoredConfig implements EventConfig {
    
    static final Singleton<LocalEventStoredConfig> FACTORY = new Singleton<>(LocalEventStoredConfig::new);
    
    @Override
    public EventDispatcher getEventDispatcher() {
        return LocalEventStoredDispatcher.FACTORY.getInstance();
    }
}
