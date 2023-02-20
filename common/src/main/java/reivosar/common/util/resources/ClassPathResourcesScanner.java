package reivosar.common.util.resources;

import com.google.common.reflect.ClassPath;
import reivosar.common.util.cache.Cache;
import reivosar.common.util.cache.CacheFactory;

import java.io.IOException;
import java.util.Set;

final class ClassPathResourcesScanner {
    
    private static final Cache<FileExtension, ResourceFiles> SCANNED_RESOURCES;
    private static final Set<String> EXCLUDE_FILE_EXTENSIONS = Set.of("class");
    
    static {
        try {
            final Cache<FileExtension, ResourceFiles> localCache = CacheFactory.getEternalLocalCache();
            for (final ClassPath.ResourceInfo resourceInfo :
                    ClassPath.from(Thread.currentThread().getContextClassLoader()).getResources()) {
                putResourceFileMap(localCache, resourceInfo);
            }
            SCANNED_RESOURCES = localCache;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private static void putResourceFileMap(
            final Cache<FileExtension, ResourceFiles> localCache,
            final ClassPath.ResourceInfo resourceInfo) {
        final FileExtension fileExtension = FileExtension.of(resourceInfo.getResourceName());
        if (EXCLUDE_FILE_EXTENSIONS.contains(fileExtension.extension())) {
            return;
        }
        final ResourceFiles resourceFiles = localCache.get(fileExtension).orElse(new ResourceFiles());
        resourceFiles.add(resourceInfo.url());
        localCache.put(fileExtension, resourceFiles);
    }
    
    private ClassPathResourcesScanner() {
        // This constructor must be private
    }
    
    static ResourceFiles scanBy(final FileExtension fileExtension) {
        return SCANNED_RESOURCES.get(fileExtension).orElse(new ResourceFiles());
    }
}
