package reivosar.common.util.event;

import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;
import reivosar.common.util.reflect.ClassDescriptor;
import reivosar.common.util.reflect.ClassDescriptors;
import reivosar.common.util.reflect.ClassResources;

import java.util.Collection;
import java.util.List;

class LocalEventHandlerCache {
    
    private static final Cache<String, LocalEventHandler> CACHE;
    
    static {
        CACHE = CacheFactory.getLRULocalCache();
    }
    
    static LocalEventHandler getLocalEventHandlers(final Event event) {
        return CACHE.get(getCacheKey(event)).orElse(createCache(event));
    }
    
    private static void put(final Event event, final LocalEventHandler localEventHandler) {
        CACHE.put(getCacheKey(event), localEventHandler);
    }
    
    private static String getCacheKey(final Event event) {
        return event.getClass().getName();
    }
    
    private static LocalEventHandler createCache(final Event event) {
        final ClassDescriptors classDescriptors = ClassResources.findByMethodParameters(event);
        if (!classDescriptors.hasDescriptor()) {
            return getLocalEventHandler(event, List.of());
        }
        return getLocalEventHandler(event, classDescriptors.getClassDescriptors());
    }
    
    private static LocalEventHandler getLocalEventHandler(final Event event, final Collection<ClassDescriptor> classDescriptors) {
        final LocalEventHandler localEventHandlers = new LocalEventHandler(classDescriptors);
        put(event, localEventHandlers);
        return localEventHandlers;
    }
}
