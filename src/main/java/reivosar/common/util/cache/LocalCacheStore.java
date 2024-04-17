package reivosar.common.util.cache;

import reivosar.common.lang.ObjectUtil;
import reivosar.common.data.model.Model;

import java.util.*;
import java.util.stream.Collectors;

class LocalCacheStore<K, V> extends Model {
    
    private final Map<K, Collection<V>> cacheMap;
    
    public LocalCacheStore(final Map<K, Collection<V>> cacheMap) {
        this.cacheMap = Collections.synchronizedMap(cacheMap);
    }
    
    Collection<V> get(final K key) {
        ObjectUtil.requireNonNull("key", key);
        final Collection<V> values = this.cacheMap.get(key);
        if (values == null) {
            return List.of();
        }
        return getCollection(values);
    }
    
    private Set<V> getCollection(final Collection<V> values) {
        return Collections.unmodifiableSet(new LinkedHashSet<>(values));
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
        return getCollection(cacheMap.values().stream()
                .flatMap(vs -> new HashSet<>(vs).stream())
                .collect(Collectors.toCollection(LinkedHashSet::new)));
    }
    
    void put(final K key, final V value) {
        ObjectUtil.requireNonNull("key", key);
        ObjectUtil.requireNonNullAndEmpty("value", value);
        Collection<V> values = ObjectUtil.defaultIfNull(this.cacheMap.get(key), new LinkedHashSet<>());
        values.add(value);
        this.cacheMap.put(key, values);
    }
    
    void clear(final K key) {
        ObjectUtil.requireNonNull("key", key);
        this.cacheMap.compute(key, (s, o) -> null);
    }
    
    void clearAll() {
        this.cacheMap.clear();
    }
}
