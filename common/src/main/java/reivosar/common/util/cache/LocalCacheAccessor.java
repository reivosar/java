package reivosar.common.util.cache;

import java.util.Collection;

class LocalCacheAccessor<K, V> extends CacheAccessor<K, V> {
    
    private final LocalCacheStore<K, V> localCacheStore;
    
    LocalCacheAccessor(final LocalCacheStore<K, V> localCacheStore) {
        this.localCacheStore = localCacheStore;
    }
    
    @Override
    Collection<V> get(final K key) {
        return this.localCacheStore.get(key);
    }
    
    @Override
    boolean exists(final K key) {
        return localCacheStore.exists(key);
    }
    
    @Override
    void put(final K key, final V value) {
        this.localCacheStore.put(key, value);
    }
    
    @Override
    Collection<K> getAllKeys() {
        return this.localCacheStore.getAllKeys();
    }
    
    @Override
    void clear(final K key) {
        this.localCacheStore.clear(key);
    }
}
