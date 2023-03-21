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
    default V firstValue(K key) throws NullPointerException {
        return get(key).firstValue();
    }
    
    /**
     * Returns the value if present, otherwise returns {@code null}.
     *
     * @param key key with associated value returned
     * @return the value if present, otherwise {@code null}
     */
    default V nullableFirstValue(K key) {
        return get(key).nullableFirstValue();
    }
    
    /**
     * Associates the specified value with the specified key in this cache. This method
     * is equivalent to calling {@code put(key, value, null)}.
     *
     * @param key   the key with which to associate the value
     * @param value the value to be associated with the key
     */
    void put(K key, V value);
    
    /**
     * Associates the specified value with the specified key in this cache, and specifies
     * an expiration time for the cache entry. If the specified cache expiration is null,
     * the entry will not expire. If an entry with the same key already exists in the cache,
     * its value will be replaced with the new value and expiration time.
     *
     * @param key          the key with which to associate the value
     * @param value        the value to be associated with the key
     * @param cacheExpires the expiration time for the cache entry, or null if the entry should not expire
     */
    void put(K key, V value, CacheExpires cacheExpires);
    
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
