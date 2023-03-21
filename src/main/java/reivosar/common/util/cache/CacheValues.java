package reivosar.common.util.cache;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * A class that wraps a collection of values for caching, using generics.
 *
 * @param <V> the type of value to be cached
 */
public class CacheValues<V> extends Model {
    
    private final Collection<CacheValue<V>> values;
    
    static <V> CacheValues<V> from(final Collection<V> values) {
        return new CacheValues<>(values.stream().map(CacheValue::new).toList());
    }
    
    CacheValues(final Collection<CacheValue<V>> values) {
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
        return values.stream()
                .filter(CacheValue::isAvailableCache)
                .map(CacheValue::getIfCacheAvailable)
                .toList();
    }
    
    /**
     * Returns the first cached value wrapped in an Optional object.
     *
     * @return An Optional object containing the first cached value, if one exists.
     */
    public Optional<V> findFirst() {
        return values().stream().findFirst();
    }
    
    /**
     * Returns the first cached value or throws a NullPointerException if no values are cached.
     *
     * @return The first cached value.
     * @throws NullPointerException if no values are cached.
     */
    public V firstValue() throws NullPointerException {
        return findFirst().orElseThrow(NullPointerException::new);
    }
    
    /**
     * Returns a new instance of {@link CacheValues} that contains all values from the cache
     * that satisfy the given predicate.
     *
     * @param predicate the predicate used to filter the cache values
     * @return a new instance of {@link CacheValues} that contains all values from the cache
     * that satisfy the given predicate
     */
    public CacheValues<V> filter(final Predicate<V> predicate) {
        return CacheValues.from(values().stream().filter(predicate).toList());
    }
    
    /**
     * Returns the value if present, otherwise returns {@code null}.
     *
     * @return the value if present, otherwise {@code null}
     */
    public V nullableFirstValue() {
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
