package reivosar.common.util.event;

import reivosar.common.util.promise.Promise;

public interface EventPublisher {
    
    <T> Promise<T> publish(Event event);
}
