package reivosar.common.resources;

import reivosar.common.data.model.Model;
import reivosar.common.lang.ObjectUtil;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * A class that represents a properties file and provides methods to access its key-value pairs.
 * This class encapsulates a {@link ResourceFile} and uses it to load properties from a file.
 * <p>
 * If the properties file cannot be loaded due to issues with the underlying file,
 * the exception is not thrown, as the problem is related to the file itself and does not impact the process.
 * </p>
 */
public class PropertyFile extends Model {

    private final ResourceFile resourceFile;
    private final Properties properties;

    /**
     * Constructs a new {@code PropertyFile} by loading properties from the specified {@link ResourceFile}.
     * <p>
     * If an {@link IOException} occurs while loading the properties, it is silently ignored,
     * as the issue is considered to be related to the file rather than the process itself.
     * </p>
     *
     * @param resourceFile the resource file from which to load properties
     */
    PropertyFile(final ResourceFile resourceFile) {
        this.resourceFile = resourceFile;
        this.properties = new Properties();
        resourceFile.useInputStream(inputStream -> {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                // Do nothing
            }
        });
    }

    /**
     * Returns the name of the properties file.
     *
     * @return the file name of the properties file
     */
    public String fileName() {
        return this.resourceFile.fileName();
    }

    /**
     * Returns the base name of the file, which is the file name without its extension.
     *
     * @return the base name of the file, without the extension
     */
    public String baseName() {
        return this.resourceFile.baseName();
    }

    /**
     * Retrieves the value associated with the specified key from the properties file.
     *
     * @param key the key to look up in the properties file
     * @return an {@link Optional} containing the value if the key is found, or an empty {@code Optional} if not
     * @throws NullPointerException if the key is {@code null}
     */
    public Optional<String> getProperty(final String key) {
        ObjectUtil.requireNonNull("key", key);
        return Optional.ofNullable(properties.getProperty(key));
    }

    /**
     * Retrieves the value associated with the specified key from the properties file,
     * returning the provided default value if the key is not found.
     *
     * @param key          the key to look up in the properties file
     * @param defaultValue the default value to return if the key is not found
     * @return the value associated with the key, or the default value if the key is not found
     * @throws NullPointerException if the key is {@code null}
     */
    public String getProperty(final String key, final String defaultValue) {
        ObjectUtil.requireNonNull("key", key);
        return getProperty(key).orElse(defaultValue);
    }

    /**
     * Checks if the properties file contains a value for the specified key.
     *
     * @param key the key to check for in the properties file
     * @return {@code true} if the key is present, {@code false} otherwise
     * @throws NullPointerException if the key is {@code null}
     */
    public boolean containsKey(final String key) {
        ObjectUtil.requireNonNull("key", key);
        return getProperty(key).isPresent();
    }
}
