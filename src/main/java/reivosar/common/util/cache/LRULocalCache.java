package reivosar.common.util.cache;

final class LRULocalCache<K, V> extends CacheTemplate<K, V> {
    
    private static final int CACHE_MAX_SIZE = 100;
    
    LRULocalCache() {
        this(CACHE_MAX_SIZE);
    }
    
    LRULocalCache(final int maxSize) {
        super(new LRULocalCacheAccessorFactory<K, V>().createCacheAccessor(maxSize));
    }
}
