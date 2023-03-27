package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;
import reivosar.common.util.lang.ObjectUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryEventStore implements EventStore {
    
    private static final Map<EventDescriptorIdentify, EventDescriptor> EVENTS;
    
    static {
        EVENTS = new ConcurrentHashMap<>();
    }
    
    private final LockableFunction lockableFunction;
    
    InMemoryEventStore() {
        this.lockableFunction = new LockableFunction();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean create(final Event event) {
        ObjectUtil.requireNonNull("Event", event);
        return lockableFunction.withLock(() -> {
            final EventDescriptor eventDescriptor =
                    DefaultEventDescriptor.createNew(event);
            EVENTS.put(eventDescriptor.getEventDescriptorIdentify(), eventDescriptor);
            return EVENTS.containsKey(eventDescriptor.getEventDescriptorIdentify());
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<EventDescriptor> findById(final EventDescriptorIdentify eventDescriptorIdentify) {
        ObjectUtil.requireNonNull("EventDescriptorIdentify", eventDescriptorIdentify);
        return lockableFunction.withLock(() -> Optional.ofNullable(EVENTS.get(eventDescriptorIdentify)));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasUncompletedEvent() {
        return lockableFunction.withLock(() -> !EVENTS.isEmpty());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(final EventDescriptor eventDescriptor) {
        ObjectUtil.requireNonNull("EventDescriptor", eventDescriptor);
        return lockableFunction.withLock(() -> {
            Optional<EventDescriptor> original = findById(eventDescriptor.getEventDescriptorIdentify());
            EVENTS.put(eventDescriptor.getEventDescriptorIdentify(), eventDescriptor);
            return original.isEmpty() || !original.get().equals(eventDescriptor);
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<EventDescriptor> getUnpublishedEvents() {
        return lockableFunction.withLock(() -> Collections.unmodifiableCollection(
                EVENTS.values().stream()
                        .filter(eventDescriptor -> !eventDescriptor.isPublished())
                        .sorted(Comparator.comparing(EventDescriptor::getStoredOn))
                        .collect(Collectors.toList())));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<EventDescriptor> getUncompletedEvents() {
        return lockableFunction.withLock(() -> Collections.unmodifiableCollection(
                EVENTS.values().stream()
                        .filter(eventDescriptor -> !eventDescriptor.isCompleted())
                        .sorted(Comparator.comparing(EventDescriptor::getStoredOn))
                        .collect(Collectors.toList())));
    }
}
