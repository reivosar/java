package reivosar.common.util.event;

import reivosar.common.util.model.Model;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

class DefaultEventDescriptor extends Model implements EventDescriptor {
    
    private final EventDescriptorIdentify eventDescriptorIdentify;
    private final Event event;
    private final Instant storedOn;
    private final Instant publishedOn;
    private final Instant completedOn;
    
    static EventDescriptor createNew(final Event event) {
        return new DefaultEventDescriptor(
                ()-> UUID.randomUUID().toString(),
                event,
                Instant.now(),
                null,
                null);
    }
    
    static EventDescriptor publishedBy(final EventDescriptor event) {
        return new DefaultEventDescriptor(
                event.getEventDescriptorIdentify(),
                event.getEvent(),
                event.getStoredOn(),
                Instant.now(),
                null);
    }
    
    static EventDescriptor completedBy(final EventDescriptor event) {
        return new DefaultEventDescriptor(
                event.getEventDescriptorIdentify(),
                event.getEvent(),
                event.getStoredOn(),
                event.getPublishedOn().orElse(null),
                Instant.now());
    }
    
    private DefaultEventDescriptor(final EventDescriptorIdentify eventDescriptorIdentify,
                                   final Event event,
                                   final Instant storedOn,
                                   final Instant publishedOn,
                                   final Instant completedOn) {
        this.eventDescriptorIdentify = eventDescriptorIdentify;
        this.event = event;
        this.storedOn = storedOn;
        this.publishedOn = publishedOn;
        this.completedOn = completedOn;
    }
    
    @Override
    public EventDescriptorIdentify getEventDescriptorIdentify() {
        return eventDescriptorIdentify;
    }
    
    @Override
    public Event getEvent() {
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
