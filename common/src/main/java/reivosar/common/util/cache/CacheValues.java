package reivosar.common.util.cache;

import reivosar.common.util.model.Model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CacheValues extends Model {
    
    public static final CacheValues EMPTY = new CacheValues(List.of());
    
    private final Collection<?> values;
    
    public CacheValues(final Collection<?> values) {
        Objects.requireNonNull(values);
        this.values = values;
    }
    
    @SuppressWarnings("unchecked")
    public <T> Collection<T> values(final Class<T> mappingClass) {
        return values.stream()
                .filter(o -> o.getClass().isAssignableFrom(mappingClass))
                .map(o -> (T) o)
                .collect(Collectors.toUnmodifiableList());
    }
    
    public int count() {
        return values.size();
    }
}
