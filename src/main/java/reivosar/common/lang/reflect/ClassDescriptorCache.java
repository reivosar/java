package reivosar.common.lang.reflect;

import com.google.common.reflect.ClassPath;

import java.io.IOException;
import java.util.*;

class ClassDescriptorCache {

    private static final Collection<String> EXCLUDED_PACKAGES = Set.of(
            "apache",
            "checkerframework",
            "fasterxml",
            "google",
            "javax",
            "junit",
            "picocli",
            "qos",
            "slf4j",
            "springframework");

    private static final Map<String, ClassDescriptor> CACHE;

    static {
        final Map<String, ClassDescriptor> map = new HashMap<>();
        try {
            ClassPath.from(Thread.currentThread().getContextClassLoader()).getAllClasses().stream()
                    .filter(classInfo -> EXCLUDED_PACKAGES.stream()
                            .noneMatch(s -> classInfo.getName().contains(s)))
                    .forEach(classInfo -> {
                        try {
                            map.put(classInfo.getName(), ClassDescriptorFactory.create(classInfo.load()));
                        } catch (Throwable throwable) {
                            // Do nothing
                        }
                    });
            CACHE = Collections.unmodifiableMap(map);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    static Map<String, ClassDescriptor> getCache() {
        return CACHE;
    }
}
