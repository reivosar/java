package reivosar.common.util.cache;

final class EternalLocalCache<K, V> extends CacheTemplate<K, V> {
    
    EternalLocalCache() {
        super(new EternalLocalCacheAccessorFactory<K, V>().createCacheAccessor());
    }
}
