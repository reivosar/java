package reivosar.common.util.event;

class LocalEventConfig implements EventConfig {
    
    @Override
    public EventDispatcher getEventDispatcher() {
        return new LocalEventDispatcher();
    }
}
