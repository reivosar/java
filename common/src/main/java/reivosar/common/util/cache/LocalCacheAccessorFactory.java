package reivosar.common.util.cache;

import java.util.concurrent.ConcurrentHashMap;

class LocalCacheAccessorFactory<K, V> {
    
    LocalCacheAccessor<K, V> createCacheAccessor() {
        return new LocalCacheAccessor<>(new LocalCacheStore<>(new ConcurrentHashMap<>()));
    }
}
