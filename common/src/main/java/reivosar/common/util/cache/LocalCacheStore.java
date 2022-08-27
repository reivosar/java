package reivosar.common.util.cache;

import reivosar.common.util.model.Model;

import java.util.*;
import java.util.stream.Collectors;

class LocalCacheStore extends Model {
    
    private final Map<Object, Collection<Object>> cacheMap;
    
    public LocalCacheStore(final Map<Object, Collection<Object>> cacheMap) {
        this.cacheMap = cacheMap;
    }
    
    Collection<Object> get(final Object key) {
        final Collection<Object> values = this.cacheMap.get(key);
        if (values == null) {
            return List.of();
        }
        return values.stream().collect(Collectors.toUnmodifiableList());
    }
    
    boolean containsKey(final Object key) {
        return cacheMap.containsKey(key);
    }
    
    boolean exists(final Object key) {
        return containsKey(key) && !get(key).isEmpty();
    }
    
    Collection<Object> getAllKeys() {
        return cacheMap.keySet();
    }
    
    void put(final Object key, final Object... value) {
        Collection<Object> values = this.cacheMap.get(key);
        if (values == null || values.isEmpty()) {
            values = new LinkedList<>();
        }
        values.addAll(Arrays.asList(value));
        this.cacheMap.put(key, values);
    }
    
    void clear(final Object key) {
        this.cacheMap.remove(key);
    }
}
