package reivosar.common.util.cache;

import org.apache.commons.collections4.map.LRUMap;

import java.util.Collections;

class LRULocalCacheAccessorFactory<K, V> {
    
    LocalCacheAccessor<K, V> createCacheAccessor(final int maxSize) {
        return new LocalCacheAccessor<>(new LocalCacheStore<>(Collections.synchronizedMap(new LRUMap<>(maxSize))));
    }
}
