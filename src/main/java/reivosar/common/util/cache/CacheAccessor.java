package reivosar.common.util.cache;

import java.util.Collection;

abstract class CacheAccessor<K, V> {
    
    abstract Collection<CacheValue<V>> get(K key);
    
    abstract boolean exists(K key);
    
    abstract void put(K key, CacheValue<V> value);
    
    abstract Collection<K> getAllKeys();
    
    abstract Collection<CacheValue<V>> getAllValues();
    
    abstract void clear(K key);
    
    abstract void clearAll();
}
