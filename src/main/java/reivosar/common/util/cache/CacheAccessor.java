package reivosar.common.util.cache;

import java.util.Collection;

abstract class CacheAccessor<K, V> {
    
    abstract Collection<V> get(K key);
    
    abstract boolean exists(K key);
    
    abstract void put(K key, V value);
    
    abstract Collection<K> getAllKeys();
    
    abstract void clear(K key);
}
