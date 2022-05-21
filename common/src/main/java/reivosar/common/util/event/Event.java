package reivosar.common.util.event;

import java.time.LocalDateTime;
import java.util.Collection;

public interface Event
{
    String getEventId();
    
    int getEventVersion();
    
    Collection<String> getEventTopics();
    
    LocalDateTime getEventOccurredTime();
}
