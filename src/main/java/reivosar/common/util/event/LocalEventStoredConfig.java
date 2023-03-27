package reivosar.common.util.event;

class LocalEventStoredConfig implements EventConfig {
    
    @Override
    public EventDispatcher getEventDispatcher() {
        return LocalEventStoredDispatcher.getInstance();
    }
}
