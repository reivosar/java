package reivosar.common.lang.reflect;

import com.google.common.reflect.ClassPath;
import reivosar.common.resources.PropertyFiles;

import java.io.IOException;
import java.util.*;

class ClassDescriptorCache {

    private static final String DEFAULT_TARGET_TOP_LEVEL_PACKAGE = "reivosar";
    private static final Map<String, ClassDescriptor> CACHE;

    static {
        try {
            final Map<String, ClassDescriptor> map = new HashMap<>();
            final Set<String> targetTopLevelPackages = getTargetTopLevelPackages();
            for (ClassPath.ClassInfo classInfo : ClassPath.from(Thread.currentThread().getContextClassLoader()).getAllClasses()) {
                if (targetTopLevelPackages.stream().anyMatch(packageName ->
                        classInfo.getPackageName().startsWith(packageName))) {
                    try {
                        map.put(classInfo.getName(), ClassDescriptorFactory.create(classInfo.load()));
                    } catch (Throwable throwable) {
                        // Do nothing
                    }
                }
            }
            CACHE = Collections.unmodifiableMap(map);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static Set<String> getTargetTopLevelPackages() {
        final Set<String> loadPackageNames = new HashSet<>();
        loadPackageNames.add(DEFAULT_TARGET_TOP_LEVEL_PACKAGE);
        // Read targetTopLevelPackages from property file
        PropertyFiles.getProperty("targetTopLevelPackages").ifPresent(packageNames -> {
            for (final String splintedPackageName : packageNames.split(",")) {
                loadPackageNames.add(splintedPackageName.trim());
            }
        });
        return loadPackageNames;
    }

    static Map<String, ClassDescriptor> getCache() {
        return CACHE;
    }
}
