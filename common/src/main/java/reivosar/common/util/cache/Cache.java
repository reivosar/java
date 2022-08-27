package reivosar.common.util.cache;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * An interface that defines general cache operations.
 */
public interface Cache {
    
    /**
     * Returns the default cache implementation.
     *
     * @return default cache implementation
     */
    static Cache getDefaultCache() {
        return new LocalCache();
    }
    
    /**
     * Checks if the specified key exists in the cache.
     *
     * @param key key with associated value returned
     * @return true if Key exists in cache; false otherwise.
     */
    boolean exists(@Nonnull Object key);
    
    /**
     * Returns the value to which this cache maps the given key.
     * Typically, specifies the type to which the return value will be cast.
     *
     * @param key  key with associated value returned
     * @return value in cache
     */
    <T> CacheValues<T> get(@Nonnull Object key);
    
    /**
     * Associates a given value with a given key in this cache.
     * If the cache previously contained a mapping for this key, the old value
     * is replaced with the specified value.
     *
     * @param key   key with associated value returned
     * @param value value to be associated with the specified key
     */
    void put(@Nonnull Object key, @Nonnull Object value);
    
    /**
     * Retrieve all keys in cache.
     *
     * @return all keys in cache
     */
    Collection<Object> getAllKeys();
    
    /**
     * Clears the cache associated with the specified key.
     *
     * @param key key of cache to clear
     */
    void clear(@Nonnull Object key);
    
    /**
     * Clear all caches.
     */
    void clearAll();
}
