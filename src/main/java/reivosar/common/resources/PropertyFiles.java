package reivosar.common.resources;

import reivosar.common.lang.ObjectUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * Class for accessing property files on the classpath
 */
public final class PropertyFiles {

    private static final Collection<PropertyFile> LOADED_PROPERTY_FILES;
    private static final Properties PROPERTIES;

    static {
        final Properties properties = new Properties();
        final Set<PropertyFile> loadedPropertyFiles = new HashSet<>();
        loadPropertyFiles(properties, loadedPropertyFiles);
        PROPERTIES = properties;
        LOADED_PROPERTY_FILES = Collections.unmodifiableSet(loadedPropertyFiles);
    }

    private static void loadPropertyFiles(
            final Properties properties,
            final Set<PropertyFile> loadedPropertyFiles) {
        ClassPathResourcesScanner.scanBy(new FileExtension("properties"))
                .resourceFiles()
                .forEach(file -> file.useInputStream(inputStream -> {
                            try {
                                properties.load(inputStream);
                                loadedPropertyFiles.add(new PropertyFile(file));
                            } catch (IOException e) {
                                // Do nothing
                            }
                        })
                );
    }

    private PropertyFiles() {
        // This constructor must be private
    }

    /**
     * Finds and returns a {@link PropertyFile} with the specified name from the loaded property files.
     * <p>
     * This method searches through the loaded property files by comparing the provided name with both
     * the full file name and the base name (name without extension). The comparison is case-insensitive.
     * </p>
     *
     * @param name the name of the property file to find; can be the full file name or the base name
     * @return the {@link PropertyFile} with the specified name
     * @throws NullPointerException  if the name is {@code null}
     * @throws FileNotFoundException if no {@link PropertyFile} with the specified name is found
     */
    public static PropertyFile findFile(String name) throws FileNotFoundException {
        ObjectUtil.requireNonNull("name", name);
        return LOADED_PROPERTY_FILES.stream()
                .filter(propertyFile -> propertyFile.fileName().equalsIgnoreCase(name) ||
                        propertyFile.baseName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new FileNotFoundException("PropertyFile with name " + name + " not found"));
    }

    /**
     * Returns the value in this property list with the specified key value.
     *
     * @param key the property key
     * @return the value in this property list with the specified key value.
     */
    public static Optional<String> getProperty(final String key) {
        ObjectUtil.requireNonNull("key", key);
        return Optional.ofNullable(PROPERTIES.getProperty(key));
    }

    /**
     * Returns the value in this property list with the specified key value.
     * If no value exists in the property file, the default value is returned
     *
     * @param key          the property key
     * @param defaultValue defaultValue
     * @return the value in this property list with the specified key value.
     */
    public static String getProperty(final String key, final String defaultValue) {
        ObjectUtil.requireNonNull("key", key);
        return getProperty(key).orElse(defaultValue);
    }

    /**
     * Determines if the specified key exists.
     *
     * @param key the property key
     * @return {@code true} The key exists {@code false} otherwise
     */
    public static boolean containsKey(final String key) {
        ObjectUtil.requireNonNull("key", key);
        return getProperty(key).isPresent();
    }
}
