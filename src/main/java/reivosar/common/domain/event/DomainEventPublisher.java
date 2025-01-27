package reivosar.common.domain.event;

import reivosar.common.async.event.EventPublisher;

public interface DomainEventPublisher<E extends DomainEvent> extends EventPublisher<E> {
}
