package reivosar.common.util.event;

import java.io.Serializable;

@FunctionalInterface
public interface StoredEventId extends Serializable {
    
    String asString();
}
