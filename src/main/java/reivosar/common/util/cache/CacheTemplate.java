package reivosar.common.util.cache;

import reivosar.common.util.lang.ObjectUtil;

import javax.annotation.Nonnull;
import java.util.Collection;

abstract class CacheTemplate<K, V> implements Cache<K, V> {
    
    private final CacheAccessor<K, V> cacheAccessor;
    
    protected CacheTemplate(final CacheAccessor<K, V> cacheAccessor) {
        this.cacheAccessor = cacheAccessor;
    }
    
    @Override
    public synchronized final boolean exists(final K key) {
        ObjectUtil.requireNonNull("key", key);
        return cacheAccessor.exists(key);
    }
    
    @Override
    public synchronized final CacheValues<V> get(final K key) {
        ObjectUtil.requireNonNull("key", key);
        if (!exists(key)) {
            return CacheValues.empty();
        }
        return new CacheValues<>(cacheAccessor.get(key));
    }
    
    @Override
    public synchronized final void put(final K key, final V value) {
        ObjectUtil.requireNonNull("key", key);
        ObjectUtil.requireNonNullAndEmpty("value", value);
        put(key, value, CacheExpires.EXTERNAl);
    }
    
    @Override
    public synchronized final void put(final K key, final V value, final CacheExpires cacheExpires) {
        ObjectUtil.requireNonNull("key", key);
        ObjectUtil.requireNonNullAndEmpty("value", value);
        ObjectUtil.requireNonNullAndEmpty("CacheExpires", cacheExpires);
        final CacheValue<V> cacheValue = new CacheValue<>(value, cacheExpires);
        if (cacheValue.isAvailableCache()) {
            cacheAccessor.put(key, cacheValue);
        }
    }
    
    @Override
    public synchronized final Collection<K> getAllKeys() {
        return cacheAccessor.getAllKeys();
    }
    
    @Override
    public synchronized final CacheValues<V> getAllValues() {
        return new CacheValues<>(cacheAccessor.getAllValues());
    }
    
    @Override
    public synchronized final void clear(@Nonnull final K key) {
        ObjectUtil.requireNonNull("key", key);
        cacheAccessor.clear(key);
    }
    
    @Override
    public synchronized final void clearAll() {
        cacheAccessor.clearAll();
    }
}
