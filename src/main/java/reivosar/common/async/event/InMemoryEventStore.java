package reivosar.common.async.event;

import reivosar.common.lang.ObjectUtil;
import reivosar.common.lang.Singleton;
import reivosar.common.lang.function.LockableFunction;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class InMemoryEventStore<E extends Event> implements EventStore<E> {

    static final Singleton<InMemoryEventStore<? extends Event>> SINGLETON = new Singleton<>(InMemoryEventStore::new);

    private static final Map<EventDescriptorIdentify, EventDescriptor<? extends Event>> EVENTS;

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
    public boolean append(final E event) {
        ObjectUtil.requireNonNull("event", event);
        return lockableFunction.lockOn(() -> {
            final EventDescriptor<E> eventDescriptor = DefaultEventDescriptor.createNew(event);
            EVENTS.put(eventDescriptor.getEventDescriptorIdentify(), eventDescriptor);
            return EVENTS.containsKey(eventDescriptor.getEventDescriptorIdentify());
        });
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Optional<EventDescriptor<E>> readEventById(final EventDescriptorIdentify eventDescriptorIdentify) {
        ObjectUtil.requireNonNull("eventDescriptorIdentify", eventDescriptorIdentify);
        return lockableFunction.lockOn(() -> Optional.ofNullable((EventDescriptor<E>) EVENTS.get(eventDescriptorIdentify)));
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
    public boolean applyEventResult(final EventDescriptor<E> eventDescriptor) {
        ObjectUtil.requireNonNull("eventDescriptor", eventDescriptor);
        return lockableFunction.lockOn(() -> {
            final Optional<EventDescriptor<E>> original = readEventById(eventDescriptor.getEventDescriptorIdentify());
            if (original.isPresent() && original.get().equals(eventDescriptor)) {
                return false;
            }
            return ObjectUtil.isNotEmpty(EVENTS.put(eventDescriptor.getEventDescriptorIdentify(), eventDescriptor));
        });
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public Collection<EventDescriptor<E>> getUncompletedEvents() {
        return lockableFunction.lockOn(() -> EVENTS.values().stream()
                .filter(eventDescriptor -> !eventDescriptor.isCompleted())
                .filter(eventDescriptor -> eventDescriptor.getEvent() != null)
                .map(eventDescriptor -> (EventDescriptor<E>) eventDescriptor)
                .sorted(Comparator.comparing(EventDescriptor::getStoredOn))
                .collect(Collectors.toList()));
    }
}
