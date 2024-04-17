package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventStoredConfig implements EventConfig {
    
    static final Singleton<LocalEventStoredConfig> SINGLETON = new Singleton<>(LocalEventStoredConfig::new);
    
    @Override
    public EventDispatcher getEventDispatcher() {
        return LocalEventStoredDispatcher.SINGLETON.getInstance();
    }
}
