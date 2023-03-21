package reivosar.common.util.cache;

import reivosar.common.util.model.Model;

class CacheValueWrapper<V> extends Model {
    
    private final V value;
    private final CacheExpires cacheExpires;
    
    CacheValueWrapper(final V value, final CacheExpires cacheExpires) {
        this.value = value;
        this.cacheExpires = cacheExpires;
    }
    
    boolean isCacheAvailable() {
        return getIfCacheAvailable() != null;
    }
    
    V getIfCacheAvailable() {
        if (cacheExpires.isBeforeThanNow()) {
            return null;
        }
        return value;
    }
}