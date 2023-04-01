package reivosar.common.util.reflect;

import com.google.common.reflect.ClassPath;
import reivosar.common.util.function.VoidConsumer;
import reivosar.common.util.promise.Promise;

import java.io.IOException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class ClassDescriptorCache {
    
    static synchronized Map<String, ClassDescriptor> getCache()  {
        final Map<String, ClassDescriptor> map = new HashMap<>();
        try {
            Promise.all(ClassPath.from(Thread.currentThread().getContextClassLoader())
                    .getAllClasses().stream()
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
