package reivosar.common.util.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

/**
 * Class for accessing property files on the classpath
 */
public class PropertyFiles {
    
    private static final Properties PROPERTIES;
    
    static {
        PROPERTIES = new Properties();
        ResourceFilesScanner.get(ResourceType.PROPERTIES)
                .resourceFiles()
                .forEach(resourceFile -> {
                            try (final InputStream input = new FileInputStream(resourceFile.toFile())) {
                                PROPERTIES.load(input);
                            } catch (IOException e) {
                                // Do nothing
                            }
                        }
                );
    }
    
    /**
     * Determines if the specified key exists.
     *
     * @param key key in property files
     * @return {@code true} The key exists {@code false} otherwise
     */
    public static boolean containsKey(final String key) {
        Objects.requireNonNull(key, "key must not be null");
        return PROPERTIES.containsKey(key);
    }
}
