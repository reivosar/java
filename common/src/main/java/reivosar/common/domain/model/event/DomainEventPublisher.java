package reivosar.common.domain.model.event;

import reivosar.common.domain.model.Identity;
import reivosar.common.util.promise.Promise;

public interface DomainEventPublisher
{
	<ID extends Identity<ID>, ENTITY extends EventableEntity<ID, ENTITY>>
	void asyncPublish(ENTITY entity);

	<ID extends Identity<ID>, ENTITY extends EventableEntity<ID, ENTITY>>
    Promise<Object> awaitPublish(ENTITY entity);
}
