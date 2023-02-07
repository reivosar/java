package reivosar.common.util.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Class for accessing property files on the classpath
 */
public class PropertyFiles {
    
    private static final Collection<ResourceFile> LOADED_PROPERTY_FILES;
    private static final Properties PROPERTIES;
    
    static {
        final Properties properties = new Properties();
        final Set<ResourceFile> loadedPropertyFiles = new HashSet<>();
        loadPropertyFiles(properties, loadedPropertyFiles);
        PROPERTIES = properties;
        LOADED_PROPERTY_FILES = Collections.unmodifiableSet(loadedPropertyFiles);
    }
    
    private static void loadPropertyFiles(
            final Properties properties,
            final Set<ResourceFile> loadedPropertyFiles) {
        ClassPathResourcesScanner.scanBy(ResourceType.PROPERTIES)
                .resourceFiles()
                .forEach(file -> {
                            try (final InputStream input = new FileInputStream(file.toFile())) {
                                properties.load(input);
                                loadedPropertyFiles.add(file);
                            } catch (IOException e) {
                                // Do nothing
                            }
                        }
                );
    }
    
    private PropertyFiles() {
        // This constructor should be private
    }
    
    /**
     * Returns the property files being loaded
     *
     * @return property files
     */
    public static Collection<ResourceFile> loadedPropertyFiles() {
        return LOADED_PROPERTY_FILES;
    }
    
    /**
     * Returns the value in this property list with the specified key value.
     *
     * @param key the property key
     * @return the value in this property list with the specified key value.
     */
    public static Optional<String> getProperty(final String key) {
        Objects.requireNonNull(key, "key must not be null");
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
        Objects.requireNonNull(key, "key must not be null");
        return getProperty(key).orElse(defaultValue);
    }
    
    /**
     * Determines if the specified key exists.
     *
     * @param key the property key
     * @return {@code true} The key exists {@code false} otherwise
     */
    public static boolean containsKey(final String key) {
        Objects.requireNonNull(key, "key must not be null");
        return PROPERTIES.containsKey(key);
    }
}