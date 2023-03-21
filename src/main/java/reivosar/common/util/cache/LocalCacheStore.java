package reivosar.common.util.cache;

import reivosar.common.util.lang.ObjectUtil;
import reivosar.common.util.model.Model;

import java.util.*;
import java.util.stream.Collectors;

class LocalCacheStore<K, V> extends Model {
    
    private final Map<K, Collection<V>> cacheMap;
    
    public LocalCacheStore(final Map<K, Collection<V>> cacheMap) {
        this.cacheMap = cacheMap;
    }
    
    Collection<V> get(final K key) {
        ObjectUtil.requireNonNull("key", key);
        final Collection<V> values = this.cacheMap.get(key);
        if (values == null) {
            return List.of();
        }
        return values.stream().toList();
    }
    
    boolean containsKey(final K key) {
        ObjectUtil.requireNonNull("key", key);
        return cacheMap.containsKey(key);
    }
    
    boolean exists(final K key) {
        ObjectUtil.requireNonNull("key", key);
        return containsKey(key) && !get(key).isEmpty();
    }
    
    Collection<K> keySet() {
        return cacheMap.keySet();
    }
    
    Collection<V> values() {
        return cacheMap.values().stream()
                .flatMap(vs -> new HashSet<>(vs).stream())
                .collect(Collectors.toSet());
    }
    
    void put(final K key, final V value) {
        ObjectUtil.requireNonNull("key", key);
        ObjectUtil.requireNonNullAndEmpty("value", value);
        Collection<V> values = this.cacheMap.get(key);
        if (values == null || values.isEmpty()) {
            values = new LinkedHashSet<>();
        }
        values.add(value);
        this.cacheMap.put(key, values);
    }
    
    void clear(final K key) {
        ObjectUtil.requireNonNull("key", key);
        this.cacheMap.remove(key);
    }
}
