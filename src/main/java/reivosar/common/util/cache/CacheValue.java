package reivosar.common.util.cache;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

class CacheValue<V> extends Model {
    
    private final V value;
    private final CacheExpires cacheExpires;
    
    CacheValue(final V value) {
        this(value, null);
    }
    
    CacheValue(final V value, final CacheExpires cacheExpires) {
        this.value = value;
        this.cacheExpires = ObjectUtil.defaultIfNull(cacheExpires, CacheExpires.EXTERNAl);
    }
    
    V getIfCacheAvailable() {
        return isAvailableCache() ? value : null;
    }
    
    boolean isAvailableCache() {
        return cacheExpires.isAfterNow();
    }
}
