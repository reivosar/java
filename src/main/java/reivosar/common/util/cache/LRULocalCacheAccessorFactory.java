package reivosar.common.util.cache;

import org.apache.commons.collections4.map.LRUMap;

class LRULocalCacheAccessorFactory<K, V> {
    
    LocalCacheAccessor<K, V> createCacheAccessor(final int maxSize) {
        return new LocalCacheAccessor<>(new LocalCacheStore<>(new LRUMap<>(maxSize)));
    }
}
