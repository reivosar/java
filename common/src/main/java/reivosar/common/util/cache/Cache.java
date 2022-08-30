package reivosar.common.util.cache;

import javax.annotation .Nonnull;
import java.util.Collection;

/**
 * An interface that defines general cache operations.
 *
 * @param <K> key type in cache
 * @param <V> value type in cache
 */
public interface Cache<K, V> {
    
    /**
     * Returns a local eternal thread safe cache. This cache will remain in memory unless cleared.
     *
     * @return default cache implementation
     */
    static <K, V> Cache<K, V> getEternalLocalCache() {
        return new EternalLocalCache<>();
    }
    
    /**
     * Returns a fixed maximum size thread safe cache. this cache removes the least recently used entry
     * if an entry is added when full.
     *
     * @return default cache implementation
     */
    static <K, V> Cache<K, V> getLRULocalCache() {
        return new LRULocalCache<>();
    }
    
    /**
     * Returns a fixed maximum size thread safe cache. this cache removes the least recently used entry
     * if an entry is added when full.
     *
     * @param maxSize the maximum size of the cache
     * @return default cache implementation
     */
    static <K, V> Cache<K, V> getLRULocalCache(int maxSize) {
        return new LRULocalCache<>(maxSize);
    }
    
    /**
     * Checks if the specified key exists in the cache.
     *
     * @param key key with associated value returned
     * @return true if Key exists in cache; false otherwise.
     */
    boolean exists(@Nonnull K key);
    
    /**
     * Returns the value to which this cache maps the given key.
     * Typically, specifies the type to which the return value will be cast.
     *
     * @param key key with associated value returned
     * @return value in cache
     */
    CacheValues<V> get(@Nonnull K key);
    
    /**
     * Associates a given value with a given key in this cache.
     * If the cache previously contained a mapping for this key, the old value
     * is replaced with the specified value.
     *
     * @param key   key with associated value returned
     * @param value value to be associated with the specified key
     */
    void put(@Nonnull K key, @Nonnull V value);
    
    /**
     * Retrieve all keys in cache.
     *
     * @return all keys in cache
     */
    Collection<K> getAllKeys();
    
    /**
     * Clears the cache associated with the specified key.
     *
     * @param key key of cache to clear
     */
    void clear(@Nonnull K key);
    
    /**
     * Clear all caches.
     */
    void clearAll();
}
