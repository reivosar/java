package reivosar.common.async.event;

import reivosar.common.lang.reflect.ClassDescriptor;
import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;

import java.util.Collection;
import java.util.List;

class LocalEventHandlerCache {

    private static final Cache<String, LocalEventHandler<? extends Event>> CACHE;

    static {
        CACHE = CacheFactory.getLRULocalCache();
    }

    @SuppressWarnings("unchecked")
    static <E extends Event> LocalEventHandler<E> getLocalEventHandlers(final E event) {
        return (LocalEventHandler<E>) CACHE.getOrPut(getCacheKey(event), createCache(event)).nullableFirstValue();
    }

    private static <E extends Event> void put(final Event event, final LocalEventHandler<E> localEventHandler) {
        CACHE.put(getCacheKey(event), localEventHandler);
    }

    private static <E extends Event> String getCacheKey(final E event) {
        return event.getClass().getName();
    }

    private static <E extends Event> LocalEventHandler<E> createCache(final E event) {
        final Collection<ClassDescriptor> classDescriptors = EventHandlerClassResources.findByEvent(event);
        if (classDescriptors.isEmpty()) {
            return getLocalEventHandler(event, List.of());
        }
        return getLocalEventHandler(event, classDescriptors);
    }

    private static <E extends Event> LocalEventHandler<E> getLocalEventHandler(final E event,
                                                                               final Collection<ClassDescriptor> classDescriptors) {
        final LocalEventHandler<E> localEventHandlers = new LocalEventHandler<>(classDescriptors);
        put(event, localEventHandlers);
        return localEventHandlers;
    }
}
