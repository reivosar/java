package reivosar.common.util.event;

import reivosar.common.util.model.Model;

import java.util.UUID;

class UUIDEventDescriptorIdentify extends Model implements EventDescriptorIdentify {
    
    private final UUID uuid;
    
    UUIDEventDescriptorIdentify() {
        this.uuid = UUID.randomUUID();
    }
    
    UUIDEventDescriptorIdentify(final UUID uuid) {
        this.uuid = uuid;
    }
    
    @Override
    public String asString() {
        return uuid.toString();
    }
}
