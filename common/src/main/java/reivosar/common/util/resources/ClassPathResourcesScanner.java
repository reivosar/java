package reivosar.common.util.resources;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class ClassPathResourcesScanner {
    
    private static final Map<FileExtension, ResourceFiles> SCANNED_RESOURCES;
    
    static {
        try {
            final Map<FileExtension, ResourceFiles> resourceFilesMap = new HashMap<>();
            ClassPath.from(Thread.currentThread().getContextClassLoader())
                    .getResources()
                    .forEach(resourceInfo -> {
                        final FileExtension fileExtension = FileExtension.of(resourceInfo.getResourceName());
                        final ResourceFiles resourceFiles = resourceFilesMap.containsKey(fileExtension) ?
                                resourceFilesMap.get(fileExtension) : new ResourceFiles();
                        resourceFiles.add(resourceInfo.url());
                        resourceFilesMap.put(fileExtension, resourceFiles);
                    });
            SCANNED_RESOURCES = Collections.unmodifiableMap(resourceFilesMap);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    private ClassPathResourcesScanner() {
        // This constructor must be private
    }
    
    static ResourceFiles scanBy(final FileExtension fileExtension) {
        if (!SCANNED_RESOURCES.containsKey(fileExtension)) {
            return new ResourceFiles();
        }
        return SCANNED_RESOURCES.get(fileExtension);
    }
}
