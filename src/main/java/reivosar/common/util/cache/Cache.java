package reivosar.common.util.cache;

import java.util.Collection;
import java.util.Optional;

/**
 * An interface that defines general cache operations.
 *
 * @param <K> key type in cache
 * @param <V> value type in cache
 */
public interface Cache<K, V> {
    
    /**
     * Checks if the specified key exists in the cache.
     *
     * @param key key with associated value returned
     * @return true if Key exists in cache; false otherwise.
     */
    boolean exists(K key);
    
    /**
     * Returns the value to which this cache maps the given key.
     * Typically, specifies the type to which the return value will be cast.
     *
     * @param key key with associated value returned
     * @return value in cache
     */
    CacheValues<V> get(K key);
    
    /**
     * Returns the first cached value wrapped in an Optional object.
     *
     * @param key key with associated value returned
     * @return An Optional object containing the first cached value, if one exists.
     */
    default Optional<V> findFirst(K key) {
        return get(key).findFirst();
    }
    
    /**
     * Returns the first cached value or throws a NullPointerException if no values are cached.
     *
     * @param key key with associated value returned
     * @return The first cached value.
     * @throws NullPointerException if no values are cached.
     */
    default V value(K key) throws NullPointerException {
        return get(key).value();
    }
    
    /**
     * Returns the value if present, otherwise returns {@code null}.
     *
     * @param key key with associated value returned
     * @return the value if present, otherwise {@code null}
     */
    default V nullableValue(K key) {
        return get(key).nullableValue();
    }
    
    /**
     * Associates a given value with a given key in this cache.
     * If the cache previously contained a mapping for this key, the old value
     * is replaced with the specified value.
     *
     * @param key   key with associated value returned
     * @param value value to be associated with the specified key
     */
    void put(K key, V value);
    
    /**
     * Retrieve all keys in cache.
     *
     * @return all keys in cache
     */
    Collection<K> getAllKeys();
    
    /**
     * Retrieve all values in cache.
     *
     * @return all keys in values
     */
    CacheValues<V> getAllValues();
    
    /**
     * Clears the cache associated with the specified key.
     *
     * @param key key of cache to clear
     */
    void clear(K key);
    
    /**
     * Clear all caches.
     */
    void clearAll();
}
