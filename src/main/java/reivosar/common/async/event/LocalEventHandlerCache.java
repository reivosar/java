package reivosar.common.async.event;

import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;
import reivosar.common.lang.reflect.ClassDescriptor;

import java.util.Collection;
import java.util.List;

class LocalEventHandlerCache {
    
    private static final Cache<String, LocalEventHandler> CACHE;
    
    static {
        CACHE = CacheFactory.getLRULocalCache();
    }
    
    static LocalEventHandler getLocalEventHandlers(final Event event) {
        return CACHE.getOrPut(getCacheKey(event), createCache(event)).nullableFirstValue();
    }
    
    private static void put(final Event event, final LocalEventHandler localEventHandler) {
        CACHE.put(getCacheKey(event), localEventHandler);
    }
    
    private static String getCacheKey(final Event event) {
        return event.getClass().getName();
    }
    
    private static LocalEventHandler createCache(final Event event) {
        final Collection<ClassDescriptor> classDescriptors = EventHandlerClassResources.findByEvent(event);
        if (classDescriptors.isEmpty()) {
            return getLocalEventHandler(event, List.of());
        }
        return getLocalEventHandler(event, classDescriptors);
    }
    
    private static LocalEventHandler getLocalEventHandler(final Event event,
                                                          final Collection<ClassDescriptor> classDescriptors) {
        final LocalEventHandler localEventHandlers = new LocalEventHandler(classDescriptors);
        put(event, localEventHandlers);
        return localEventHandlers;
    }
}
