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
        return lockableFunction.with(() -> {
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
        return lockableFunction.with(() -> Optional.ofNullable(EVENTS.get(eventDescriptorIdentify)));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasUncompletedEvent() {
        return lockableFunction.with(() -> !EVENTS.isEmpty());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(final EventDescriptor eventDescriptor) {
        ObjectUtil.requireNonNull("EventDescriptor", eventDescriptor);
        return lockableFunction.with(() -> {
            Optional<EventDescriptor> original = findById(eventDescriptor.getEventDescriptorIdentify());
            EVENTS.put(eventDescriptor.getEventDescriptorIdentify(), eventDescriptor);
            return original.isEmpty() || !original.get().equals(eventDescriptor);
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<EventDescriptor> getUncompletedEvents() {
        return lockableFunction.with(() -> Collections.unmodifiableCollection(
                EVENTS.values().stream()
                        .filter(eventDescriptor -> !eventDescriptor.isCompleted())
                        .sorted(Comparator.comparing(EventDescriptor::getStoredOn))
                        .collect(Collectors.toList())));
    }
}
