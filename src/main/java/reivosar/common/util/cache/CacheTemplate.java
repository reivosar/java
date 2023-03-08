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
    public final boolean exists(@Nonnull final K key) {
        ObjectUtil.requireNonNull("key", key);
        return cacheAccessor.exists(key);
    }
    
    @Override
    public final CacheValues<V> get(@Nonnull final K key) {
        ObjectUtil.requireNonNull("key", key);
        if (!exists(key)) {
            return CacheValues.empty();
        }
        return new CacheValues<>(cacheAccessor.get(key));
    }
    
    @Override
    public final void put(@Nonnull final K key, @Nonnull final V values) {
        ObjectUtil.requireNonNull("key", key);
        ObjectUtil.requireNonNullAndEmpty("values", values);
        cacheAccessor.put(key, values);
    }
    
    @Override
    public final Collection<K> getAllKeys() {
        return cacheAccessor.getAllKeys();
    }
    
    @Override
    public final void clear(@Nonnull final K key) {
        ObjectUtil.requireNonNull("key", key);
        cacheAccessor.clear(key);
    }
    
    @Override
    public final void clearAll() {
        getAllKeys().forEach(cacheAccessor::clear);
    }
}
