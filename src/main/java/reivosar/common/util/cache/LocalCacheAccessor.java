package reivosar.common.util.cache;

import java.util.Collection;

class LocalCacheAccessor<K, V> extends CacheAccessor<K, V> {
    
    private final LocalCacheStore<K, CacheValue<V>> localCacheStore;
    
    LocalCacheAccessor(final LocalCacheStore<K, CacheValue<V>> localCacheStore) {
        this.localCacheStore = localCacheStore;
    }
    
    @Override
    Collection<CacheValue<V>> get(final K key) {
        return this.localCacheStore.get(key);
    }
    
    @Override
    boolean exists(final K key) {
        return localCacheStore.exists(key);
    }
    
    @Override
    void put(final K key, final CacheValue<V> value) {
        this.localCacheStore.put(key, value);
    }
    
    @Override
    Collection<K> getAllKeys() {
        return this.localCacheStore.keySet();
    }
    
    @Override
    Collection<CacheValue<V>> getAllValues() {
        return this.localCacheStore.values();
    }
    
    @Override
    void clear(final K key) {
        this.localCacheStore.clear(key);
    }
    
    @Override
    void clearAll() {
        this.localCacheStore.clearAll();
    }
}
