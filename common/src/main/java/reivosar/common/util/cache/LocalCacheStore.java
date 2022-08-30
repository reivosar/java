package reivosar.common.util.cache;

import reivosar.common.util.model.Model;

import java.util.*;

class LocalCacheStore<K, V> extends Model {
    
    private final Map<K, Collection<V>> cacheMap;
    
    public LocalCacheStore(final Map<K, Collection<V>> cacheMap) {
        this.cacheMap = cacheMap;
    }
    
    Collection<V> get(final K key) {
        final Collection<V> values = this.cacheMap.get(key);
        if (values == null) {
            return List.of();
        }
        return values;
    }
    
    boolean containsKey(final K key) {
        return cacheMap.containsKey(key);
    }
    
    boolean exists(final K key) {
        return containsKey(key) && !get(key).isEmpty();
    }
    
    Collection<K> getAllKeys() {
        return cacheMap.keySet();
    }
    
    void put(final K key, final V value) {
        Collection<V> values = this.cacheMap.get(key);
        if (values == null || values.isEmpty()) {
            values = new LinkedHashSet<>();
        }
        values.add(value);
        this.cacheMap.put(key, values);
    }
    
    void clear(final K key) {
        this.cacheMap.remove(key);
    }
}
