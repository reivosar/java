package reivosar.common.util.cache;

import reivosar.common.util.model.Model;

import java.util.Collection;
import java.util.List;

public class CacheValues<T> extends Model {
    
    public static final CacheValues<?> EMPTY = new CacheValues<>(List.of());

    private final Collection<T> values;
    
    public CacheValues(final Collection<T> values) {
        this.values = values;
    }
}
