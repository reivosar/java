package reivosar.common.async.event;

import reivosar.common.data.model.Model;

import java.util.UUID;

class UUIDEventDescriptorIdentify extends Model implements EventDescriptorIdentify {
    
    private final UUID uuid;
    
    UUIDEventDescriptorIdentify() {
        this(UUID.randomUUID());
    }
    
    UUIDEventDescriptorIdentify(final UUID uuid) {
        this.uuid = uuid;
    }
    
    @Override
    public String asString() {
        return uuid.toString();
    }
}
