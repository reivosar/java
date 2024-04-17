package reivosar.common.async.event;

import reivosar.common.lang.Singleton;

class LocalEventConfig implements EventConfig {
    
    static final Singleton<LocalEventConfig> SINGLETON = new Singleton<>(LocalEventConfig::new);
    
    @Override
    public EventDispatcher getEventDispatcher() {
        return LocalEventDispatcher.FACTORY.getInstance();
    }
}
