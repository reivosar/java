package reivosar.common.util.event;


import reivosar.common.domain.model.ValueObject;
import reivosar.common.domain.model.time.DateTime;

public class EventOccurredTime extends ValueObject<EventOccurredTime> {
    final DateTime value;
    
    public EventOccurredTime() {
        this(DateTime.now());
    }
    
    public EventOccurredTime(DateTime value) {
        this.value = value;
    }
}