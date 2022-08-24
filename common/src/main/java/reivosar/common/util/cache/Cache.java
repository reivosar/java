package reivosar.common.util.cache;

import java.util.Optional;

/**
 * An interface that defines general cache operations.
 */
public interface Cache {
    
    /**
     * Checks if the specified key exists in the cache.
     *
     * @param key
     * @return true if Key exists in cache; false otherwise.
     */
    boolean exists(Object key);
    
    /**
     * Returns the value to which this cache maps the given key.
     * Typically, specifies the type to which the return value will be cast.
     * The value is returned as a nullable Optional type.
     *
     * @param key  key with associated value returned
     * @param type required return type
     * @param <T>  value type
     * @return value in cache
     */
    <T> Optional<T> get(Object key, Class<T> type);
    
    /**
     * Associates a given value with a given key in this cache.
     * If the cache previously contained a mapping for this key, the old value
     * is replaced with the specified value.
     *
     * @param key   key with associated value returned
     * @param value value to be associated with the specified key
     */
    void put(Object key, Object value);
    
    /**
     * Clears the cache associated with the specified key.
     *
     * @param key key of cache to clear
     */
    void clear(Object key);
    
    /**
     * Clear all caches.
     */
    void clearAll();
}
