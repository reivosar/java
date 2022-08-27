package reivosar.common.util.cache;

import reivosar.common.util.model.Model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class CacheValues<V> extends Model {
    
    private final Collection<V> values;
    
    public CacheValues(final Collection<V> values) {
        Objects.requireNonNull(values);
        this.values = values;
    }
    
    public static <V> CacheValues<V> empty() {
        return new CacheValues<>(List.of());
    }
    
    public Collection<V> values() {
        return values.stream().collect(Collectors.toUnmodifiableList());
    }
    
    public Optional<V> first() {
        return values.stream().findFirst();
    }
    
    public boolean isNotEmpty() {
        return !isEmpty();
    }
    
    public boolean isEmpty() {
        return this.values.isEmpty();
    }
    
    public int count() {
        return values.size();
    }
}
