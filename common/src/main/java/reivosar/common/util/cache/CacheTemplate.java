package reivosar.common.util.cache;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Objects;

abstract class CacheTemplate<K, V> implements Cache<K, V> {
    
    private final CacheAccessor<K, V> cacheAccessor;
    
    protected CacheTemplate(final CacheAccessor<K, V> cacheAccessor) {
        this.cacheAccessor = cacheAccessor;
    }
    
    @Override
    public final boolean exists(@Nonnull final K key) {
        Objects.requireNonNull(key, "key must not be null");
        return cacheAccessor.exists(key);
    }
    
    @Override
    public final CacheValues<V> get(@Nonnull final K key) {
        Objects.requireNonNull(key, "key must not be null");
        if (!exists(key)) {
            return CacheValues.empty();
        }
        return new CacheValues<>(cacheAccessor.get(key));
    }
    
    @Override
    public final void put(@Nonnull final K key, @Nonnull final V values) {
        Objects.requireNonNull(key, "key must not be null");
        Objects.requireNonNull(values, "values must not be null");
        cacheAccessor.put(key, values);
    }
    
    @Override
    public final Collection<K> getAllKeys() {
        return cacheAccessor.getAllKeys();
    }
    
    @Override
    public final void clear(@Nonnull final K key) {
        Objects.requireNonNull(key, "key must not be null");
        cacheAccessor.clear(key);
    }
    
    @Override
    public final void clearAll() {
        getAllKeys().forEach(cacheAccessor::clear);
    }
}
