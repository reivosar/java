package reivosar.common.async.event;

import reivosar.common.lang.reflect.ClassDescriptor;
import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;

import java.util.Collection;
import java.util.List;

class LocalEventHandlerCache {

    private static final Cache<String, LoadedClassPathEventHandler<? extends Event>> CACHE;

    static {
        CACHE = CacheFactory.getLRULocalCache();
    }

    @SuppressWarnings("unchecked")
    static <E extends Event> LoadedClassPathEventHandler<E> getLocalEventHandlers(final E event) {
        return (LoadedClassPathEventHandler<E>) CACHE.getOrPut(getCacheKey(event),
                createCache(event)).nullableFirstValue();
    }

    private static <E extends Event> void put(final Event event,
                                              final LoadedClassPathEventHandler<E> loadedClassPathEventHandler) {
        CACHE.put(getCacheKey(event), loadedClassPathEventHandler);
    }

    private static <E extends Event> String getCacheKey(final E event) {
        return event.getClass().getName();
    }

    private static <E extends Event> LoadedClassPathEventHandler<E> createCache(final E event) {
        final Collection<ClassDescriptor> classDescriptors = LocalEventHandlerRegistry.findByEvent(event);
        if (classDescriptors.isEmpty()) {
            return getLocalEventHandler(event, List.of());
        }
        return getLocalEventHandler(event, classDescriptors);
    }

    private static <E extends Event> LoadedClassPathEventHandler<E> getLocalEventHandler(final E event,
                                                                                         final Collection<ClassDescriptor> classDescriptors) {
        final LoadedClassPathEventHandler<E> loadedClassPathEventHandlers = new LoadedClassPathEventHandler<>(classDescriptors);
        put(event, loadedClassPathEventHandlers);
        return loadedClassPathEventHandlers;
    }
}
