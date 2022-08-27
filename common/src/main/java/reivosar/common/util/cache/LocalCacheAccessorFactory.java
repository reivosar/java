package reivosar.common.util.cache;

import java.util.concurrent.ConcurrentHashMap;

class LocalCacheAccessorFactory {
    
    LocalCacheAccessor createCacheAccessor() {
        return new LocalCacheAccessor(new LocalCacheStore(new ConcurrentHashMap<>()));
    }
}
