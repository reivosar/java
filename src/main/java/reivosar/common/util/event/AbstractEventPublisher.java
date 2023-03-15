package reivosar.common.util.event;

import reivosar.common.util.promise.Promise;

import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * An abstract implementation of {@link EventPublisher} that provides a method for
 * generating an event supplier, which subclasses can use to publish the event.
 */
public abstract class AbstractEventPublisher implements EventPublisher {
    
    private final EventDispatcher eventDispatcher;
    
    protected AbstractEventPublisher(final EventDispatcher eventDispatcher) {
        this.eventDispatcher = eventDispatcher;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final Promise<Void> publish(final Collection<Event> events) {
        return Promise.all(events.stream()
                .map(event -> (Supplier<Void>) () -> {
                    eventDispatcher.dispatch(event);
                    return (Void) null;
                }).collect(Collectors.toList())
        );
    }
}
