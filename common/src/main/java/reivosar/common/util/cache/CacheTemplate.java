package reivosar.common.util.cache;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.Objects;

abstract class CacheTemplate implements Cache {
    
    private final CacheAccessor cacheAccessor;
    
    protected CacheTemplate(final CacheAccessor cacheAccessor) {
        this.cacheAccessor = cacheAccessor;
    }
    
    @Override
    public final boolean exists(@Nonnull final Object key) {
        Objects.requireNonNull(key);
        return cacheAccessor.exists(key);
    }
    
    @Override
    public final CacheValues get(@Nonnull final Object key) {
        Objects.requireNonNull(key);
        if (!exists(key)) {
            return CacheValues.EMPTY;
        }
        return new CacheValues(cacheAccessor.get(key));
    }
    
    @Override
    public final void put(@Nonnull final Object key, @Nonnull final Object values) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(values);
        cacheAccessor.put(key, values);
    }
    
    @Override
    public final Collection<Object> getAllKeys() {
        return cacheAccessor.getAllKeys();
    }
    
    @Override
    public final void clear(@Nonnull final Object key) {
        Objects.requireNonNull(key);
        cacheAccessor.clear(key);
    }
    
    @Override
    public final void clearAll() {
        getAllKeys().forEach(cacheAccessor::clear);
    }
}
