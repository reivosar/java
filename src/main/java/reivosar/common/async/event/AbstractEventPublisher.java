package reivosar.common.async.event;

import reivosar.common.lang.function.VoidConsumer;
import reivosar.common.async.promise.Promise;

import java.util.Collection;
import java.util.stream.Collectors;

abstract class AbstractEventPublisher implements EventPublisher {
    
    private final EventConfig eventConfig;
    
    protected AbstractEventPublisher(final EventConfig eventConfig) {
        this.eventConfig = eventConfig;
    }
    
    @Override
    public final Promise<Void> publish(final Collection<Event> events) {
        return Promise.all(
                events.stream()
                        .map(event -> (VoidConsumer) () ->
                                doPublishEvent(eventConfig, event)
                        )
                        .collect(Collectors.toList())
        );
    }
    
    protected abstract void doPublishEvent(final EventConfig eventConfig, final Event event);
}
