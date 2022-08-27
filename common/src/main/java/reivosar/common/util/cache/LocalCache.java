package reivosar.common.util.cache;

final class LocalCache<K, V> extends CacheTemplate<K, V> {
    
    LocalCache() {
        super(new LocalCacheAccessorFactory<K, V>().createCacheAccessor());
    }
}
