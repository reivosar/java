package reivosar.common.util.cache;

import reivosar.common.util.model.Model;

import java.util.*;

/**
 * First-class collection of cache values.
 *
 * @param <V> cache values type
 */
public class CacheValues<V> extends Model {
    
    private final Collection<V> values;
    
    /**
     * Constructor for initializing fields.
     *
     * @param values cache values
     */
    public CacheValues(final Collection<V> values) {
        Objects.requireNonNull(values, "values must not be null");
        this.values = values;
    }
    
    /**
     * Returns empty cache values.
     *
     * @return empty cache values
     * @param <V> cache values type
     */
    public static <V> CacheValues<V> empty() {
        return new CacheValues<>(List.of());
    }
    
    /**
     * Returns all cache values.
     *
     * @return cache values
     */
    public Collection<V> all() {
        return Collections.unmodifiableCollection(values);
    }
    
    /**
     * Returns the first value added from the cache.
     *
     * @return the first cache value
     */
    public Optional<V> first() {
        return values.stream().findFirst();
    }
    
    /**
     * Returns true if this collection contains elements.
     *
     * @return {@code true} this collection contains element {@code false} otherwise
     */
    public boolean isNotEmpty() {
        return !isEmpty();
    }
    
    /**
     * Returns true if this collection contains no elements.
     *
     * @return {@code true} this collection contains no element {@code false} otherwise
     */
    public boolean isEmpty() {
        return this.values.isEmpty();
    }
    
    /**
     * Return the number of caches.
     *
     * @return the number of caches
     */
    public int count() {
        return values.size();
    }
}
