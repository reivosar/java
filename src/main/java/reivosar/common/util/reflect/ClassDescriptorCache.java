package reivosar.common.util.reflect;

import com.google.common.reflect.ClassPath;
import reivosar.common.util.function.VoidConsumer;
import reivosar.common.util.promise.Promise;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
            Promise.all(ClassPath.from(Thread.currentThread().getContextClassLoader())
                    .getAllClasses().stream()
                    .filter(classInfo -> EXCLUDED_PACKAGES.stream()
                            .noneMatch(s -> classInfo.getName().contains(s)))
                    .map(classInfo -> (VoidConsumer) () -> {
                        try {
                            map.put(classInfo.getName(), ClassDescriptorFactory.create(classInfo.load()));
                        } catch (Throwable e) {
                            // Do nothing
                        }
                    })
                    .collect(Collectors.toList()));
            CACHE = Collections.unmodifiableMap(map);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    static synchronized Map<String, ClassDescriptor> getCache() {
        return CACHE;
    }
}
