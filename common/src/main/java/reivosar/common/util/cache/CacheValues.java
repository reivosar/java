package reivosar.common.util.cache;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A class that wraps a collection of values for caching, using generics.
 *
 * @param <V> the type of value to be cached
 */
public class CacheValues<V> extends Model {
    
    private final Collection<V> values;
    
    /**
     * Constructs a new instance of the CacheValues class using the given collection of values.
     *
     * @param values the collection of values to be cached
     * @throws NullPointerException if the given collection is null
     */
    public CacheValues(final Collection<V> values) {
        ObjectUtil.requireNonNull("values", values);
        this.values = values;
    }
    
    /**
     * Returns an empty CacheValues object.
     *
     * @param <V> The type of values to cache.
     * @return An empty CacheValues object.
     */
    public static <V> CacheValues<V> empty() {
        return new CacheValues<>(List.of());
    }
    
    /**
     * Returns an unmodifiable collection of the cached values.
     *
     * @return An unmodifiable collection of the cached values.
     */
    public Collection<V> values() {
        return Collections.unmodifiableCollection(values);
    }
    
    /**
     * Returns the first cached value wrapped in an Optional object.
     *
     * @return An Optional object containing the first cached value, if one exists.
     */
    public Optional<V> findFirst() {
        return values.stream().findFirst();
    }
    
    /**
     * Returns the first cached value or throws a NullPointerException if no values are cached.
     *
     * @return The first cached value.
     * @throws NullPointerException if no values are cached.
     */
    public V value() throws NullPointerException {
        return findFirst().orElseThrow(NullPointerException::new);
    }
    
    /**
     * Returns the value if present, otherwise returns {@code null}.
     *
     * @return the value if present, otherwise {@code null}
     */
    public V nullableValue() {
        return orElse(null);
    }
    
    /**
     * Returns the first cached value or the specified default value if no values are cached.
     *
     * @param defaultValue The default value to return if no values are cached.
     * @return The first cached value or the specified default value if no values are cached.
     */
    public V orElse(final V defaultValue) {
        return findFirst().orElse(defaultValue);
    }
    
    /**
     * Returns true if there are any cached values, false otherwise.
     *
     * @return true if there are any cached values, false otherwise.
     */
    public boolean isNotEmpty() {
        return !isEmpty();
    }
    
    /**
     * Returns true if there are no cached values, false otherwise.
     *
     * @return true if there are no cached values, false otherwise.
     */
    public boolean isEmpty() {
        return this.values.isEmpty();
    }
    
    /**
     * Returns the number of values in the cached collection.
     *
     * @return the number of values in the cached collection
     */
    public int size() {
        return values.size();
    }
}
