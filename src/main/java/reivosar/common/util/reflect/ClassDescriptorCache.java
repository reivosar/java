package reivosar.common.util.reflect;

import com.google.common.reflect.ClassPath;
import reivosar.common.util.function.VoidConsumer;
import reivosar.common.util.promise.Promise;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class ClassDescriptorCache {
    
    private static final Collection<String> EXCEPT_LOADING_CLASS_PACKAGES;
    static {
        EXCEPT_LOADING_CLASS_PACKAGES = Set.of(
                "javax",
                "org.apache",
                "com.google",
                "com.fasterxml",
                "ch.qos",
                "picocli",
                "org.checkerframework",
                "org.slf4j",
                "org.springframework");
    }
    
    static synchronized Map<String, ClassDescriptor> getCache() {
        final Map<String, ClassDescriptor> map = new HashMap<>();
        try {
            Promise.all(ClassPath.from(Thread.currentThread().getContextClassLoader())
                    .getAllClasses().stream()
                    .filter(classInfo -> EXCEPT_LOADING_CLASS_PACKAGES.stream()
                            .noneMatch(s -> classInfo.getName().startsWith(s)))
                    .map(classInfo -> (VoidConsumer) () -> {
                        try {
                            map.put(classInfo.getName(), ClassDescriptorFactory.create(classInfo.load()));
                        } catch (Throwable e) {
                            // Do nothing
                        }
                    })
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return map;
    }
}
