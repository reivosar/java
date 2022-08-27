package reivosar.common.util.cache;

import java.util.Collection;

class LocalCacheAccessor extends CacheAccessor {
    
    private final LocalCacheStore localCacheStore;
    
    LocalCacheAccessor(final LocalCacheStore localCacheStore) {
        this.localCacheStore = localCacheStore;
    }
    
    @Override
    Collection<Object> get(final Object key) {
        return this.localCacheStore.get(key);
    }
    
    @Override
    boolean exists(final Object key) {
        return localCacheStore.exists(key);
    }
    
    @Override
    void put(final Object key, final Object value) {
        this.localCacheStore.put(key, value);
    }
    
    @Override
    Collection<Object> getAllKeys() {
        return this.localCacheStore.getAllKeys();
    }
    
    @Override
    void clear(final Object key) {
        this.localCacheStore.clear(key);
    }
}
