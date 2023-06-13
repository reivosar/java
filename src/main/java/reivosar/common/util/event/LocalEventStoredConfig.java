package reivosar.common.util.event;

import reivosar.common.util.lang.Singleton;

class LocalEventStoredConfig implements EventConfig {
    
    static final Singleton<LocalEventStoredConfig> SINGLETON = new Singleton<>(LocalEventStoredConfig::new);
    
    @Override
    public EventDispatcher getEventDispatcher() {
        return LocalEventStoredDispatcher.SINGLETON.getInstance();
    }
}
