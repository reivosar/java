package reivosar.common.util.cache;

final class LocalCache extends CacheTemplate {
    
    LocalCache() {
        super(new LocalCacheAccessorFactory().createCacheAccessor());
    }
}
