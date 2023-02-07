package reivosar.common.util.resources;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

enum ResourceType {
    CLASS(".class"),
    PROPERTIES(".properties"),
    XML(".xml"),
    JSON(".json"),
    YAML(".yml", ".yaml"),
    CSV(".csv"),
    UNSUPPORTED_RESOURCE();
    
    private final Set<String> extensions;
    
    ResourceType(final String... extensions) {
        this.extensions = new HashSet<>(Arrays.asList(extensions));
    }
    
    static ResourceType of(final String resourceName) {
        return Arrays.stream(values())
                .filter(resourceType -> resourceType.extensions.stream().anyMatch(resourceName::endsWith))
                .findFirst()
                .orElse(UNSUPPORTED_RESOURCE);
    }
}
