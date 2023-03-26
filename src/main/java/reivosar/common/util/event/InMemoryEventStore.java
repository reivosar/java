package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;

import java.util.*;
import java.util.stream.Collectors;

class InMemoryEventStore implements EventStore {
    
    private static final Collection<Event> EVENTS;
    
    static {
        EVENTS = Collections.synchronizedList(new LinkedList<>());
    }
    
    private final LockableFunction lockableFunction;
    
    InMemoryEventStore() {
        this.lockableFunction = new LockableFunction();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(final Event event) {
        return lockableFunction.with(() -> EVENTS.add(event));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Event> nextEvent() {
        return lockableFunction.with(() -> getAll().stream().findFirst());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasEvent() {
        return lockableFunction.with(() -> !EVENTS.isEmpty());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(final Event event) {
        lockableFunction.with(() -> EVENTS.removeIf(storedEvent -> storedEvent.equals(event)));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<Event> getAll() {
        return lockableFunction.with(() ->
                Collections.unmodifiableCollection(
                        EVENTS.stream()
                                .sorted(Comparator.comparing(Event::eventPriority).reversed())
                                .collect(Collectors.toList()))
        );
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        lockableFunction.with(EVENTS::clear);
    }
}
