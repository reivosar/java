package reivosar.common.util.cache;

import java.util.LinkedHashMap;

class EternalLocalCacheAccessorFactory<K, V> {
    
    LocalCacheAccessor<K, V> createCacheAccessor() {
        return new LocalCacheAccessor<>(new LocalCacheStore<>(new LinkedHashMap<>()));
    }
}
