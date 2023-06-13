package reivosar.common.util.event;

import reivosar.common.util.lang.Singleton;

class LocalEventConfig implements EventConfig {
    
    static final Singleton<LocalEventConfig> SINGLETON = new Singleton<>(LocalEventConfig::new);
    
    @Override
    public EventDispatcher getEventDispatcher() {
        return LocalEventDispatcher.FACTORY.getInstance();
    }
}
