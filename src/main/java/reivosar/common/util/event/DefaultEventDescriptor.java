package reivosar.common.util.event;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

class DefaultEventDescriptor implements EventDescriptor {
    
    private final EventDescriptorIdentify eventDescriptorIdentify;
    private final Event event;
    private final Instant storedOn;
    private final Instant completedOn;
    
    static EventDescriptor createNew(final Event event) {
        return new DefaultEventDescriptor(
                ()-> UUID.randomUUID().toString(),
                event,
                Instant.now(),
                null);
    }
    
    static EventDescriptor completedBy(final EventDescriptor event) {
        return new DefaultEventDescriptor(
                event.getEventDescriptorIdentify(),
                event.getEvent(),
                event.getStoredOn(),
                Instant.now());
    }
    
    private DefaultEventDescriptor(final EventDescriptorIdentify eventDescriptorIdentify,
                                   final Event event,
                                   final Instant storedOn,
                                   final Instant completedOn) {
        this.eventDescriptorIdentify = eventDescriptorIdentify;
        this.event = event;
        this.storedOn = storedOn;
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
    public Optional<Instant> getCompletedOn() {
        return Optional.ofNullable(completedOn);
    }
}
