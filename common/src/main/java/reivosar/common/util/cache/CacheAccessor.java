package reivosar.common.util.cache;

import java.util.Collection;

abstract class CacheAccessor {
    
    abstract Collection<Object> get(Object key);
    
    abstract boolean exists(Object key);
    
    abstract void put(Object key, Object value);
    
    abstract Collection<Object> getAllKeys();
    
    abstract void clear(Object key);
}
