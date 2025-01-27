package reivosar.common.async.event;

import reivosar.common.data.model.Model;

import java.time.Instant;
import java.util.Optional;

class DefaultEventDescriptor<E extends Event> extends Model implements EventDescriptor<E> {

    private final EventDescriptorIdentify eventDescriptorIdentify;
    private final E event;
    private final Instant storedOn;
    private final Instant publishedOn;
    private final Instant completedOn;

    private DefaultEventDescriptor(final EventDescriptorIdentify eventDescriptorIdentify,
                                   final E event,
                                   final Instant storedOn,
                                   final Instant publishedOn,
                                   final Instant completedOn) {
        this.eventDescriptorIdentify = eventDescriptorIdentify;
        this.event = event;
        this.storedOn = storedOn;
        this.publishedOn = publishedOn;
        this.completedOn = completedOn;
    }

    static <E extends Event> EventDescriptor<E> createNew(final E event) {
        return new DefaultEventDescriptor<>(
                new UUIDEventDescriptorIdentify(),
                event,
                Instant.now(),
                null,
                null);
    }

    static <E extends Event> EventDescriptor<E> publishedBy(final EventDescriptor<E> event) {
        return new DefaultEventDescriptor<>(
                event.getEventDescriptorIdentify(),
                event.getEvent(),
                event.getStoredOn(),
                Instant.now(),
                null);
    }

    static <E extends Event> EventDescriptor<E> completedBy(final EventDescriptor<E> event) {
        return new DefaultEventDescriptor<>(
                event.getEventDescriptorIdentify(),
                event.getEvent(),
                event.getStoredOn(),
                event.getPublishedOn().orElse(null),
                Instant.now());
    }

    @Override
    public EventDescriptorIdentify getEventDescriptorIdentify() {
        return eventDescriptorIdentify;
    }

    @Override
    public E getEvent() {
        return event;
    }

    @Override
    public Instant getStoredOn() {
        return storedOn;
    }

    @Override
    public Optional<Instant> getPublishedOn() {
        return Optional.ofNullable(publishedOn);
    }

    @Override
    public Optional<Instant> getCompletedOn() {
        return Optional.ofNullable(completedOn);
    }
}
