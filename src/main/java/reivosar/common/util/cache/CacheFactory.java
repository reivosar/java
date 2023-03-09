package reivosar.common.util.cache;

/**
 * The CacheFactory class provides static methods for creating cache objects.
 */
public class CacheFactory {
    
    /**
     * Creates a new eternal local cache.
     *
     * @param <K> the type of the cache keys
     * @param <V> the type of the cache values
     * @return a new eternal local cache
     */
    public static <K, V> Cache<K, V> getEternalLocalCache() {
        return new EternalLocalCache<>();
    }
    
    /**
     * Creates a new LRU (least recently used) local cache with a default maximum size.
     *
     * @param <K> the type of the cache keys
     * @param <V> the type of the cache values
     * @return a new LRU local cache with a default maximum size
     */
    public static <K, V> Cache<K, V> getLRULocalCache() {
        return new LRULocalCache<>();
    }
    
    /**
     * Creates a new LRU (least recently used) local cache with the specified maximum size.
     *
     * @param <K>     the type of the cache keys
     * @param <V>     the type of the cache values
     * @param maxSize the maximum size of the cache
     * @return a new LRU local cache with the specified maximum size
     */
    public static <K, V> Cache<K, V> getLRULocalCache(int maxSize) {
        return new LRULocalCache<>(maxSize);
    }
}
