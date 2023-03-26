package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;

class InMemoryEventStore implements EventStore {
    
    private static final Collection<Event> EVENTS;
    
    static {
        EVENTS = Collections.synchronizedList(new LinkedList<>());
    }
    
    private final LockableFunction lock;
    
    public InMemoryEventStore() {
        this.lock = new LockableFunction();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void add(final Event event) {
        lock.with(() -> EVENTS.add(event));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Event> nextEvent() {
        return lock.with(() -> EVENTS.stream().findFirst());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEvent() {
        return lock.with(() ->!EVENTS.isEmpty());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final Event event) {
        lock.with(() -> EVENTS.removeIf(storedEvent -> storedEvent.equals(event)));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Event> getAll() {
        return lock.with(() -> Collections.unmodifiableCollection(EVENTS));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        lock.with(EVENTS::clear);
    }
}
