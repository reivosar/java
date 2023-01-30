package reivosar.common.util.resources;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class ResourceFilesScanner {
    
    private static final Map<ResourceType, ResourceFiles> SCANNED_RESOURCES;
    
    static {
        try {
            final Map<ResourceType, ResourceFiles> resourceFilesMap = new HashMap<>();
            ClassPath.from(Thread.currentThread().getContextClassLoader())
                    .getResources()
                    .forEach(resourceInfo -> {
                        final ResourceType resourceType = ResourceType.of(resourceInfo.getResourceName());
                        final ResourceFiles resourceFiles = resourceFilesMap.containsKey(resourceType) ?
                                resourceFilesMap.get(resourceType) : new ResourceFiles();
                        resourceFiles.add(resourceInfo.url());
                        resourceFilesMap.put(resourceType, resourceFiles);
                    });
            SCANNED_RESOURCES = Collections.unmodifiableMap(resourceFilesMap);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private ResourceFilesScanner() {
        // this constructor should be private
    }
    
    static ResourceFiles get(final ResourceType resourceType) {
        if (!SCANNED_RESOURCES.containsKey(resourceType)) {
            return new ResourceFiles();
        }
        return SCANNED_RESOURCES.get(resourceType);
    }
}
