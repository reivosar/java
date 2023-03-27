package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

class InMemoryEventStore implements EventStore {
    
    private static final Collection<StoredEvent> EVENTS;
    
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
        return lockableFunction.with(() -> EVENTS.add(new StoredEvent() {
            @Override
            public StoredEventId getStoredEventId() {
                return () -> UUID.randomUUID().toString();
            }
            
            @Override
            public Event getEvent() {
                return event;
            }
            
            @Override
            public LocalDateTime getEventStoredDateTime() {
                return LocalDateTime.now();
            }
        }));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<StoredEvent> nextEvent() {
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
    public void remove(final StoredEvent event) {
        lockableFunction.with(() -> EVENTS.removeIf(storedEvent -> storedEvent.getStoredEventId().equals(event.getStoredEventId())));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<StoredEvent> getAll() {
        return lockableFunction.with(() ->
                Collections.unmodifiableCollection(
                        EVENTS.stream()
                                .sorted(Comparator.comparing(StoredEvent::getEventStoredDateTime))
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
