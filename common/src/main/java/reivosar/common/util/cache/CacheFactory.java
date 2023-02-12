package reivosar.common.util.cache;

/**
 * Factory for creating Cache instances,
 */
public class CacheFactory {
    
    /**
     * Returns a local eternal thread safe cache. This cache will remain in memory unless cleared.
     *
     * @return default cache implementation
     */
    public static <K, V> Cache<K, V> getEternalLocalCache() {
        return new EternalLocalCache<>();
    }
    
    /**
     * Returns a fixed maximum size thread safe cache. this cache removes the least recently used entry
     * if an entry is added when full.
     *
     * @return default cache implementation
     */
    public static <K, V> Cache<K, V> getLRULocalCache() {
        return new LRULocalCache<>();
    }
    
    /**
     * Returns a fixed maximum size thread safe cache. this cache removes the least recently used entry
     * if an entry is added when full.
     *
     * @param maxSize the maximum size of the cache
     * @return default cache implementation
     */
    public static <K, V> Cache<K, V> getLRULocalCache(int maxSize) {
        return new LRULocalCache<>(maxSize);
    }
}
