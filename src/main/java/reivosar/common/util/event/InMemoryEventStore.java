package reivosar.common.util.event;

import reivosar.common.util.function.LockableFunction;
import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.lang.Singleton;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryEventStore implements EventStore {
    
    static final Singleton<InMemoryEventStore> SINGLETON = new Singleton<>(InMemoryEventStore::new);
    
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
        ObjectUtil.requireNonNull("event", event);
        return lockableFunction.lockOn(() -> {
            final EventDescriptor eventDescriptor = DefaultEventDescriptor.createNew(event);
            EVENTS.put(eventDescriptor.getEventDescriptorIdentify(), eventDescriptor);
            return EVENTS.containsKey(eventDescriptor.getEventDescriptorIdentify());
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<EventDescriptor> findById(final EventDescriptorIdentify eventDescriptorIdentify) {
        ObjectUtil.requireNonNull("eventDescriptorIdentify", eventDescriptorIdentify);
        return lockableFunction.lockOn(() -> Optional.ofNullable(EVENTS.get(eventDescriptorIdentify)));
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasUncompletedEvent() {
        return lockableFunction.lockOn(() -> !EVENTS.isEmpty());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(final EventDescriptor eventDescriptor) {
        ObjectUtil.requireNonNull("eventDescriptor", eventDescriptor);
        return lockableFunction.lockOn(() -> {
            final Optional<EventDescriptor> original = findById(eventDescriptor.getEventDescriptorIdentify());
            if (original.isPresent() && original.get().equals(eventDescriptor)) {
                return false;
            }
            return ObjectUtil.isNotEmpty(EVENTS.put(eventDescriptor.getEventDescriptorIdentify(), eventDescriptor));
        });
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<EventDescriptor> getUnpublishedEvents() {
        return lockableFunction.lockOn(() -> Collections.unmodifiableCollection(
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
        return lockableFunction.lockOn(() -> Collections.unmodifiableCollection(
                EVENTS.values().stream()
                        .filter(eventDescriptor -> !eventDescriptor.isCompleted())
                        .sorted(Comparator.comparing(EventDescriptor::getStoredOn))
                        .collect(Collectors.toList())));
    }
}
