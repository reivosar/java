package reivosar.common.util.event;

import reivosar.common.util.promise.Promise;

import java.util.function.Supplier;

/**
 * An abstract implementation of {@link EventPublisher} that provides a method for
 * generating an event supplier, which subclasses can use to publish the event.
 */
public abstract class AbstractEventPublisher implements EventPublisher {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Promise<Void> publishEvent(final Event event) {
        return Promise.resolve(eventSupplier(event));
    }
    
    /**
     * Returns a supplier that generates an event handler for the given event.
     *
     * @param event the event to generate an event handler for.
     * @return a supplier that generates an event handler for the given event.
     */
    protected abstract Supplier<Void> eventSupplier(final Event event);
}
