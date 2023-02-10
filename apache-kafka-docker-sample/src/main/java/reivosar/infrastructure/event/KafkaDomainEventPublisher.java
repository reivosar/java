package reivosar.infrastructure.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reivosar.common.domain.model.Identity;
import reivosar.common.domain.model.event.DomainEventPublisher;
import reivosar.common.domain.model.event.EventableEntity;
import reivosar.common.util.io.json.JsonUtil;
import reivosar.common.util.promise.Promise;

import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class KafkaDomainEventPublisher implements DomainEventPublisher {
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    public KafkaDomainEventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    @Override
    public <ID extends Identity<ID>, ENTITY extends EventableEntity<ID, ENTITY>> void asyncPublish(ENTITY entity) {
        entity.allEvents().forEach(e -> this.kafkaTemplate.send(e.getEventTopic(), JsonUtil.serialize(e)));
    }
    
    @Override
    public <ID extends Identity<ID>, ENTITY extends EventableEntity<ID, ENTITY>>
    Promise<Object> awaitPublish(ENTITY entity) {
        return Promise.all(entity.allEvents().stream().map(
                e -> (Supplier<Object>) () -> kafkaTemplate.send(e.getEventTopic(), JsonUtil.serialize(e)))
                .collect(Collectors.toList()));
    }
}
