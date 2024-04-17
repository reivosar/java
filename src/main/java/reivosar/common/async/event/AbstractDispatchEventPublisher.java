package reivosar.common.async.event;

abstract class AbstractDispatchEventPublisher extends AbstractEventPublisher {
    
    protected AbstractDispatchEventPublisher(final EventConfig eventConfig) {
        super(eventConfig);
    }
    
    @Override
    protected void doPublishEvent(final EventConfig eventConfig, final Event event) {
        eventConfig.getEventDispatcher().dispatch(event);
    }
}
